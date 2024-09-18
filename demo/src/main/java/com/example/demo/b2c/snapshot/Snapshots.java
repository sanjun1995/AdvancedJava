package com.example.demo.b2c.snapshot;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import com.esotericsoftware.kryo.util.Pool;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.util.ConcurrentReferenceHashMap;

/**
 * @author caozhixin
 * @date 2024/9/18 12:57
 */
public class Snapshots {
    private static final ThreadLocal<Entity<?>> holder = new ThreadLocal<>();

    private static final ConcurrentReferenceHashMap<Class<?>, Integer> classToInitBufferSize = new ConcurrentReferenceHashMap<>();

    private static final Integer defaultKryoBufferSize = 4096;

    private static final double thresholdOfResetInitBufferSize = 0.5f;

    private static final Pool<Kryo> KryoPoolHolder = new Pool<Kryo>(true, false, 200) {
        @Override
        protected Kryo create() {
            Kryo kryo = new Kryo();
            kryo.setRegistrationRequired(false);
            kryo.setReferences(true);
            kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
            return kryo;
        }
    };

    @SuppressWarnings("unchecked")
    public static <E extends Entity<?>> E snapshot() {
        return (E) holder.get();
    }

    public static <E extends Entity<?>> void snapshot(E entity) {
        if (entity == null) {
            throw new NullPointerException("entity is null");
        }
        holder.set(kryoCopy(entity));
    }

    public static <T> T kryoCopy(T origin) {
        if (origin == null) {
            return null;
        }

        Kryo kryo = KryoPoolHolder.obtain();
        try (Output output = new Output(getInitBufferSize(origin.getClass()), -1)) {
            kryo.writeObject(output, origin);

            try (Input input = new Input(output.getBuffer(), 0, output.position())) {
                setInitBufferSize(origin.getClass(), output.position());

                return (T) kryo.readObject(input, origin.getClass());
            }
        } finally {
            freeKryoResource(kryo);
        }
    }

    public static void setInitBufferSize(Class<?> clazz, int curSize) {
        Integer preSize = getInitBufferSize(clazz);
        if (defaultKryoBufferSize.equals(preSize)) {
            classToInitBufferSize.put(clazz, (int) (curSize * 1.2f));
        } else {
            int newSize = preSize - curSize > preSize * thresholdOfResetInitBufferSize
                    ? (int) ((curSize * 0.1 + preSize * 0.9))
                    : (int) (curSize * 1.05f);
            classToInitBufferSize.put(clazz, newSize);
        }
    }

    private static void freeKryoResource(Kryo kryo) {
        KryoPoolHolder.free(kryo);
    }

    public static Integer getInitBufferSize(Class<?> clazz) {
        return classToInitBufferSize.getOrDefault(clazz, defaultKryoBufferSize);
    }

    public static void clearSnapshot() {
        holder.remove();
    }
}
