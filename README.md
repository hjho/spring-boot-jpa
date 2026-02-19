# Study Spring Data JPA

https://www.inflearn.com/course/jpa-spring-data-기초
강의 기반이지만, 검색을 통한 공부.

## 1. generated
기본적인 엔티티를 조작하는 패키지.
[generated](https://github.com/hjho/spring-boot-jpa/tree/master/src/main/java/jpabasic/reserve/domain/generated)


@Entity @Table @Id @Column
* User.java

@Embeddable @Embedded @AttributeOverride
* Employee.java
* Address.java

@SecondaryTables
* Writer.java
* Intro.java
* Address.java

@Enumerated
* Hotel.java

@GeneratedValue 
* Review.java 

@SequenceGenerator
* ActivityLog.java

@TableGenerator
* AccessLog.java

## 2. collector
기본적인 엔티티에 Collector 타입을 @Embedded 하는 패키지.
[collector](https://github.com/hjho/spring-boot-jpa/tree/master/src/main/java/jpabasic/reserve/domain/collector)

@ElementCollection @CollectionTable
* Set - Role.java, GrantedPermission.java

+@OrderColumn
* List - Question.java, Choice.java

+@MapKeyColumn
* Map - Document.java, PropValue.java

## 3. relation
기본적인 엔티티에 Relation을 사용하는 패키지.
[relation](https://github.com/hjho/spring-boot-jpa/tree/master/src/main/java/jpabasic/reserve/domain/relation)

주요 테이블 구성
* Team
    * 기본테이블
* Player
    * 기본테이블
* AssignPlayer
    * Team 과 Player 의 n:m 조인 테이블

Team 의 구성요소
* @OneToOne TeamInfo
* @OneToMany List<AssignPlayer>

Player 의 구성요소
* @OneToOne PlayerCard
* @OneToMany List<PlayerPosition>

AssignPlayer 의 구성요소
* @ManyToOne Team
* @ManyToOne Player

## 4. relation 테스트
TeamTestService
* 기본적인 연관 엔티티를 조작해서 
* 기본적인 더티채킹을 통한 등록, 조회, 수정, 삭제.

PlayerTestService
* CascadeType 와 FetchType 조작해서 
* 더티채킹의 영속성 전이 설정에 따른 등록, 조회, 수정, 삭제.
* CascadeType.PERSIST, CascadeType.REMOVE
* CascadeType.DETACH, CascadeType.MERGE
* FetchType.LAZY, FetchType.EAGER

AssignPlayerTestService
* @ManyToOne 의 등록과정.

RelationTestService
* 제약조건에 따른 수정, 삭제 에러 상황.
* CascadeType.REFRESH

CategoryTestService
* 계층형 테이블의 구조 및 등록, 조회, 수정, 삭제 테스트.
* clearAndAddChildren() 이용해서 삭제해야 함.


