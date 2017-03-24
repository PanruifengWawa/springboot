# springboot
## /api/user/login 
该接口使用spring boot 自带的findBy特性

## /api/user/getList
用的是spring自带的Query接口，返回page(分页制度)

同时，在对应的ServiceImpl里面，有原生SQL，aliasToBean的示例

## /api/user/exception
里面有个1/0，用于测试ExceptionHandler中的RuntimeException处理，实现过滤拦截

## /api/user/aop
用于测试自定义注解CheckUser，同时在相应的切面UserTypeAspect里面，抛出自定义的异常AuthException，再由ExceptionHandler处理，实现过滤拦截



