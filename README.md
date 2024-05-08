## ☕ An example of application using the [cae-framework](https://github.com/clean-arch-enablers-project/cae-framework) at its 0.7.0 version.
![caelogo](https://github.com/zeluciojr/cae-framework-example/assets/60593328/def34ec8-0897-463a-bce3-031c1bfd205f)

In this example we have as layers:

<br>

- The Core layer with the application's Use Cases definition:
  - 🔗 [cae-framework-example-core-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer).

<br>

- The Adapters layer with the application's Secondary Adapters:
  - 🔗 [cae-framework-example-adapters-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-adapters-layer).

<br>

- The Assemblers layer with the application's Use Cases and their Adapters assembled:
  - 🔗 [cae-framework-example-assemblers-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-assemblers-layer)

<br>

- The Dispatchers layer with the application's Primary Adapters:
  -   🔗 [cae-framework-example-lambda-handlers-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-lambda-handlers-layer).
  -   🔗 [cae-framework-example-spring-controllers-layer](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-spring-controllers-layer).

<br>

As use cases:

<br>

- [create_new_user](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer/src/main/java/com/zeluciojr/cae_framework_example/core/use_cases/create_new_user) (FunctionUseCase)
- [make_user_inactive](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer/src/main/java/com/zeluciojr/cae_framework_example/core/use_cases/make_user_inactive) (ConsumerUseCase)
- [retrieve_all_inactive_users](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer/src/main/java/com/zeluciojr/cae_framework_example/core/use_cases/retrieve_all_inactive_users) (SupplierUseCase)
- [wipe_inactive_users](https://github.com/zeluciojr/cae-framework-example/tree/0.7.0/cae-framework-example-core-layer/src/main/java/com/zeluciojr/cae_framework_example/core/use_cases/wipe_inactive_users) (RunnableUseCase)
