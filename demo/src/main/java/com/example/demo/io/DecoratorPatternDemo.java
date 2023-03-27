package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/27 17:29
 */
// 抽象组件
interface Component {
    void operation();
}
// 具体组件
class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("ConcreteComponent operation");
    }
}
// 抽象装饰器
abstract class Decorator implements Component {
    protected Component component;
    public Decorator(Component component) {
        this.component = component;
    }
    @Override
    public void operation() {
        component.operation();
    }
}
// 具体装饰器
class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }
    @Override
    public void operation() {
        super.operation();
        System.out.println("ConcreteDecoratorA operation");
    }
}
class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component component) {
        super(component);
    }
    @Override
    public void operation() {
        super.operation();
        System.out.println("ConcreteDecoratorB operation");
    }
}
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component = new ConcreteDecoratorA(component);
        component = new ConcreteDecoratorB(component);
        component.operation();
    }
}
