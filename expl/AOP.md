# AOP

## 정의   
    1. Aspect Oriented Programming
    2. 핵심 기능에 공통 기능을 삽입하는 것 (aspect)
    3. 보안 , 로깅 , 트랜잭션등 여러 객체에서 공통으로 적용되는 로직들을 분리하여 모듈화
    
## 핵심 용어
    1. <b>Aspect</b>:    
       여러 객체에 공통으로 적용되는 공통 관점 (예. 트랜잭션, 보안)   
       Advice + Pointcut
    2. <b>Advice</b>
       언제 공통 기능들을 적용할지 정의   
       스프링 Advice 종류
       1. Around Advice : Jointpoint 앞과 뒤에 실행되는 Advice
       2. Before Advice : Jointpoint 앞에서 실행되는 Advice
       3. After Returning Advice : Jointpoint 메서드 호출이 정상적으로 종료된 뒤에 실행되는 Advice
       4. After Throwing Advice : 예외가 던져질 때 실행되는 Advice
       5. Introduction : 클래스에 인터페이스와 구현을 추가하는 특수한 Advice
    3. <b>JointPoint</b>
       Advice를 적용할 지점 (예. 메서드 호출, 필드값 변경)
       추가 예. 메서드 호출 시점, 인스턴스 생성 시점
    4. <b>Pointcut</b>
       JointPoint의 부분집합으로서 실제로 Advice가 적용되는 Jointpoint