# ๐ BeanFactory์ ApplicationContext
<br>

![๋น๊ณผ ์ดํ๋ฆฌ์ผ์ด์1](https://user-images.githubusercontent.com/52389219/152155475-00679a56-bdcf-47ca-9402-366ea13111ac.PNG)

### - BeanFactory
- #### ์คํ๋ง ์ปจํ์ด๋์ ์ต์์ ์ธํฐํ์ด์ค
- #### ์คํ๋ง ๋น์ ๊ด๋ฆฌํ๊ณ  ์กฐํํ๋ ์ญํ ์ ๋ด๋นํ๊ณ , getBean()์ ์ ๊ณตํ๋ค.
- #### ๋๋ถ๋ถ์ ๊ธฐ๋ฅ์ BeanFactory์์ ์ ๊ณตํ๋ค๊ณ  ์๊ฐํ๋ฉด ๋๋ค.

### - ApplicationContext
- #### BeanFactory์ ๊ธฐ๋ฅ์ ๋ชจ๋ ์์๋ฐ์์ ์ ๊ณตํ๋ค.
- #### ๋น์ ๊ด๋ฆฌํ๊ณ  ๊ฒ์ํ๋ ๊ธฐ๋ฅ์ BeanFactory๊ฐ ์ ๊ณตํด์ฃผ์ง๋ง ์๋ง์ ๋ถ๊ฐ๊ธฐ๋ฅ์ด ํ์ํ๋ค.


> ApplicationContext๊ฐ ์ ๊ณตํ๋ ๋ถ๊ฐ๊ธฐ๋ฅ๋ค

![๋น๊ณผ ์ดํ๋ฆฌ์ผ์ด์2](https://user-images.githubusercontent.com/52389219/152155494-45fac048-3083-4462-9800-fcf7d8d1777a.PNG)






1. ๋ฉ์ธ์ง ์์ค๋ฅผ ํ์ฉํ ๊ตญ์ ํ ๊ธฐ๋ฅ
- MessageSource๋ ๋ฉ์ธ์ง ์ค์  ํ์ผ์ ๋ชจ์์ ๊ฐ ๊ตญ๊ฐ๋ง๋ค ๋ก์ปฌ๋ผ์ด์ง์ ํจ์ผ๋ก์จ ๊ฐ ์ง์ญ์ ๋ง์ถค ๋ฉ์ธ์ง๋ฅผ ์ ๊ณต
   - ํ๊ตญ์์ ๋ค์ด์ค๋ฉด ํ๊ตญ์ด๋ก, ์์ด๊ถ์์ ๋ค์ด์ค๋ฉด ์์ด๋ก ์ถ๋ ฅํ๋ ๊ธฐ๋ฅ
   -๊ธฐ๋ณธ ๋ฉ์ธ์ง์ ๊ฒฝ์ฐ  : message.properites (์์คํ์ ์ธ์ด ๋ฐ ์ง์ญ์ ๋ง๋ ํ๋กํผํฐ ํ์ผ์ด ์กด์ฌํ์ง ์์ ๊ฒฝ์ฐ)

   - ํ๊ธ_ํ๊ตญ ๋ฉ์ธ์ง :     message_ko_KR.properties

   - ์์ด_๋ฏธ๊ตญ ๋ฉ์ธ์ง :     message_en_US.properties
   

2. ํ๊ฒฝ๋ณ์
- ๋ก์ปฌ, ๊ฐ๋ฐ, ์ด์๋ฑ์ ๊ตฌ๋ถํด์ ์ฒ๋ฆฌ 
    - Profile(์ดํ ํ๋กํ์ผ) ํ์ฑํ ๋ฐ ์ค์ 
    - Environment์ ์ญํ ์  Property(์ดํ ํ๋กํผํฐ) ์์ค ์ค์  ๋ฐ ํ๋กํผํฐ ๊ฐ์ ๊ฐ์ ธ์ค๋ ๊ฒ์๋๋ค. ์ฆ, ์ ํ๋ฆฌ์ผ์ด์์ ๋ฑ๋ก๋๋ key-value ์์ ํ๋กํผํฐ๋ค์ ์ ๊ทผํ  ์ ์๋๋ก ํด์ค๋๋ค.
  
3. ์ ํ๋ฆฌ์ผ์ด์ ์ด๋ฒคํธ
- ์ด๋ฒคํธ๋ฅผ ๋ฐํํ๊ณ  ๊ตฌ๋ํ๋ ๋ชจ๋ธ์ ํธ๋ฆฌํ๊ฒ ์ง์ํ๋ค.

4. ํธ๋ฆฌํ ๋ฆฌ์์ค ์กฐํ
- ํ์ผ, ํด๋์ค ํจ์ค, ์ธ๋ถ๋ฑ์์ ๋ฆฌ์์ค๋ฅผ ํธ๋ฆฌํ๊ฒ ์กฐํํ๋ค.





<br>

๐ ์ฐธ์กฐ : [์ธํ๋ฐ](https://www.inflearn.com/)์ **๊น์ํ๋**๊ฐ์ , [๋จ๊ถ์ฑ๋์ ์๋ฐ์์ ์] ,[๋ธ๋ก๊ทธ ์ฐธ๊ณ ](https://steady-coding.tistory.com/459)
[๋ธ๋ก๊ทธ ์ฐธ๊ณ ](https://kyun-s-world.gitbook.io/nowstart/spring/springframeworkcore/2-applicationcontext-2)
<br>
๐ ๊ฐ์ธ ๊ณต๋ถ ๊ธฐ๋ก์ฉ ๋ธ๋ก๊ทธ์๋๋ค. ์ค๋ฅ๋ ํ๋ฆฐ ๋ถ๋ถ์ด ์์ ๊ฒฝ์ฐ 
์ธ์ ๋ ์ง ๋๊ธ ํน์ ๋ฉ์ผ๋ก ์ง์ ํด์ฃผ์๋ฉด ๊ฐ์ฌํ๊ฒ ์ต๋๋ค! ๐
<br>

**๊ฐ์ธ๋ฉ๋ชจ**

