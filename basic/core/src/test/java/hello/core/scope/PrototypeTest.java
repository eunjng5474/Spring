package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

//        // destroy 실행할 필요가 있다면 직접 호출해야 함
//        prototypeBean1.destroy();
//        prototypeBean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    // @Component 없는 이유 : AnnotationConfigApplicationContext에 지정되면 스프링 빈으로 등록함
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy     // 실행 안됨
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

// 실행결과
// find prototypeBean1
// PrototypeBean.init
// find prototypeBean2
// PrototypeBean.init
// prototypeBean1 = hello.core.scope.PrototypeTest$PrototypeBean@13d4992d
// prototypeBean2 = hello.core.scope.PrototypeTest$PrototypeBean@302f7971
// org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing