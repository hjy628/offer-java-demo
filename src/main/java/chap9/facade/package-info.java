package chap9.facade;


/**
 *
 *
 外观模式
        Facade Pattern 也叫门面模式，通过一个门面(Facade)向客户端提供一个访问系统的统一接口，客户端无须关心和知晓系统内部各子模块（系统）之间的复杂关系。
        其主要目的是降低访问拥有多个子系统的复杂系统的难度。简化客户端与其之间的接口。
        外观模式将子系统中的功能抽象成一个统一的接口，客户端通过这个接口访问系统，使得系统使用起来更加容易。


        简单来说，外观模式就是将多个子系统及其之间的复杂关系和调用流程封装到一个统一的接口或类中以对外提供服务。
        涉及3种角色：
                子系统角色： 实现了子系统的功能。
                门面角色： 外观模式的核心，熟悉各个子系统的功能和调用关系并根据客户端的需求封装统一的方法来对外提供服务。
                客户角色： 通过调用Facade来完成业务功能。


 *
 *
 *
 *
 *
 *
 *
 *
 * */