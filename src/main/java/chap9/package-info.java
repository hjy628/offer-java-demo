package chap9;

/*

设计模式7个原则:
 单一职责原则、开闭原则、里氏替换原则、依赖倒转原则、接口隔离原则、合成\聚合复用原则、狄米特法则

 单一职责原则： 规定一个类只有一个职责，
 开闭原则: 规定软件中的对象（类、模块、函数等）对扩展开放，对修改封闭。这意味着一个实体允许在不改变其源码的前提下改变其行为，该特性在产品化的环境下是特别有价值的。
 里氏替换原则：对开闭原则的补充，规定了在任意父类可以出现的地方，子类都一定可以出现。关键就是抽象化，父类与子类的继承关系就是抽象化的具体表现。
 依赖倒转原则： 程序要依赖于抽象(Java中的抽象类和接口),而不依赖于具体的实现(Java中的实现类).
 接口隔离原则： 通过将不同的功能定义在不同的接口中来实现接口的隔离，这样就避免了其他类在依赖该接口(接口上定义的功能)时依赖其不需要的接口，可减少接口之间依赖的冗余性和复杂性
 合成/聚合复用原则： 通过在一个新的对象中引入（注入）已有的对象以达到类的功能复用和扩展的目的。它的设计原则是要尽量使用合成或聚合而不要使用继承来扩展类的功能。
 狄米特法则： 一个对象尽可能少地与其他对象发生相互作用，即一个对象对其他对象应该有尽可能少的了解或依赖。其核心思想在于降低模块之间的耦合度，提高模块的内聚性。








 设计模式按照其功能和使用场景可分为三大类： 创建型模式（Creational Pattern）、结构型模式(Structural Pattern)和行为型模式(Behavioral Pattern)

 创建型模式： 提供了多种优雅创建对象的方法： 工厂模式(Factory Pattern)、 抽象工厂模式(Abstract Factory Pattern)、单例模式(Singleton Pattern)、
                建造者模式(Builder Pattern)、原型模式(Prototype Pattern)

 结构性模式： 通过类和接口之间的继承和引用实现创建复杂结构对象的功能:  适配器模式(Adapter Pattern)、桥接模式(Bridge Pattern)、过滤器模式(Filter、Criteria Pattern)
                组合模式(Composite Pattern)、装饰器模式(Decorator Pattern)、外观模式(Facade Pattern)、享元模式(Flyweight Pattern)、代理模式(Proxy Pattern)

 行为型模式：  通过类之间不同的通信方式实现不同的行为方式：  责任链模式(Chain of Responsibility Pattern)、 命令模式(Command Pattern)、 解释器模式(Interpreter Pattern)、
                迭代器模式(Iterator Pattern)、 中介者模式(Mediator Pattern)、 备忘录模式(Memento Pattern)、 观察者模式(Observer Pattern)、 状态模式(State Pattern)、
                策略模式(Strategy Pattern)、模板模式(Template Pattern)、访问者模式(Visitor Pattern)





*
*
*
*
*
*
* */