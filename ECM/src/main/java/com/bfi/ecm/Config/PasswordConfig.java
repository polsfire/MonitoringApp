    package com.bfi.ecm.Config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Component;

    @Component
    public class PasswordConfig {

    @Bean
        public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    }

/*
not related to th sprng boot but to the spring it self
spring bean === object
@bean
just instance of a classe but with some metadata
managed by the spring ioc container
the metadata :
the class name
the name of the bean
the scope of the bean :(by default :singleton or prototype)
constrcutor argument :any argument that need to be passed to the constrcutor when creating the bean
properties:any propertiesd need to be set on the beanafter it s created
initilisation methode :the initializingbeaninterface lets a bean perform initialization work
after the container has set all necessay properties on the bean
Destrcution methode :implementing the disablebean interface lets  a bean get a callback when the container that contains
is distroyed
-----------
@Componenet
the classe become a bean so it s managed by the spiring the ioc container
then it can be autowired to a constrcutor
-----
private final PostService postService;
                            it will autowire the instance bellow
public PostController(PostService postService) {
    this.postService = postService;
}
i need to add @Autowired only if i have multiple constructore else it s implicitly added
-----------
@service is annotated also with @cmponent
-----------
@RestController annotead with @ controller that is annotated ith @Component
--------------------->all of the top are class level
-----
@bean used for function level
they are used usuall in a configuration clase
------
@Respository
@SpringBootapplciaiton
------
spring container decide when to create the instance when to kill it how to initilize it
 + responsible for dependency injection
 functin to ge the bean from the applciaiton is :nameofinstance.getbeandefinitionnames

the scan of hte beenis start from the package where the springabootapplciaiton @ located ifa bean is i a high level forder it will not be scanned
*/