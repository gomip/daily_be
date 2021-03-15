# SQL

## Mybatis
1. 자바에서 관계형 데이터베이스 프로그래밍을 좀더 쉽게 할수 있게 도와주는 fwk
2. 자바에서는 데이터베이스 프로그래밍을 위해 jdbc를 제공해준다.
    1. 다양한 rdb를 지원하기 때문에 세부적인 작업이 가능하게 작업별로 각각의 메소드를 호출하게 된다.
    2. 이렇다 보니 다수의 메소드를 호출하고 관련된 객체를 해제해야하는 단점이 존재   
    -> Mybatis는 jdbc 보다 좀더 편하게 사용하기 위해 개발됨
       
### Mybatis 특징
1. 간단함
2. 생산성이 좋음
3. 구조적강점 (join etc)
4. 설계 향상
5. SQL 코드가 애플리케이션 소스코드로 부터 완전 분리

### Mybatis 구성
1. 환경설정파일
   1. 매핑 설정이 어디에 있는지
   2. 디비에 어떻게 접속할건지
   3. 사용할 모델클래스들에 대한 alias 
2. 매핑설정파일
   1. 사용할 sql문들에 대한 정의
3. Session빌드 및 사용
   1. SqlSession을 통해 sql 수행 
    
### 용어 정리
1. SqlSessionFactoryBuilder : 설정파일을 읽어서 SqlSessionFactory 객체 생성
2. SqlSessionFactory : SqlSession을 만드는 역할
3. SqlSession : 실제로 sql문을 호출해주는 역할 필요시 open 및 close