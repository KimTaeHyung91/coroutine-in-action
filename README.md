# Blocking Thread, Async Thread, Coroutine 성능 테스트

---
시나리오: 외부 API 호출 시 100ms 소요, 10_000 명의 유저가 30초동안 점진적으로 요청 시 결과<br>

**blocking thread**<br>
![img.png](report/img.png)<br><br>

**@Async Thread**<br>
![img_2.png](report/img_2.png)<br>br>

**Coroutine**<br>
![img_3.png](report/img_3.png)<br><br>

**Monitoring**
![img_1.png](report/img_1.png)

---
시나리오: 외부 API 호출 시 100ms 소요, 10_000 명의 유저가 동시에 요청 시 결과<br>
**blocking thread**<br>
![img_2.png](img_2.png)

**@Async Thread**<br>
![img_1.png](img_1.png)

**Coroutine**<br>
![img.png](img.png)

---
시나리오: 외부 API 호출 시 100ms 소요, 초당 1_000 명의 유저가 60초동안 요청 시 결과<br>
**blocking thread**<br>
Container shutdown<br>

**@Async Thread**<br>
Container shutdown<br>

**Coroutine**<br>
