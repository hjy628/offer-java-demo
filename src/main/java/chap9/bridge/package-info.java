package chap9.bridge;


/**
 *
 *
 桥接模式(Bridge Pattern)通过将抽象及其实现解耦，使二者可以根据需求独立变化。
    这种类型的设计模式属于结构型模式，通过定义一个抽象和实现之间的桥接者来达到解藕的目的。

    桥接模式主要用于解决在需求多变的情况下使用继承造成类爆炸的问题，扩展起来不够灵活。
    可以通过桥接模式将抽象部分与实现部分分离，使其能够独立变化而相互之间的功能不受影响。
    具体做法是通过定义一个桥接接口，使得实体类的功能独立于接口实现类，降低它们之间的耦合度。
    JDBC和DriverManager就使用了桥接模式。


 *
 *
 *
 *
 * */