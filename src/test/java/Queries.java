public class Queries {


    /**

     1. List all employees in department D001.
     - D001 departmanındaki tüm çalışanları listele.

     2. List all employees in 'Human Resources' department.
     - 'İnsan Kaynakları' departmanındaki tüm çalışanları listele.

     3. Calculate the average salary of all employees
     - Tüm çalışanların ortalama maaşını hesapla.

     4. Calculate the average salary of all employees with gender "M"
     - "Erkek" cinsiyetindeki tüm çalışanların ortalama maaşını hesapla.

     5. Calculate the average salary of all employees with gender "F"
     - "Kadın" cinsiyetindeki tüm çalışanların ortalama maaşını hesapla.

     6. List all employees in the "Sales" department with a salary greater than 70,000.
     - Maaşı 70.000'den yüksek olan "Satış" departmanındaki tüm çalışanları listele.

     7. This query retrieves employees who have salaries between 50000 and 100000.
     - Bu sorgu, maaşı 50.000 ile 100.000 arasında olan çalışanları getirir.

     8. Calculate the average salary for each department (by department number or department name)
     - Her departmanın ortalama maaşını hesapla (departman numarasına veya departman adına göre).

     9. Calculate the average salary for each department, including department names
     - Departman adlarını da içeren her departmanın ortalama maaşını hesapla.

     10. Find all salary changes for employee with emp. no '10102'
     - '10102' iş numarasına sahip çalışanın tüm maaş değişikliklerini bul.

     11. Find the salary increases for employee with employee number '10102' (using the to_date column
     in salaries)
     - Maaş numarası '10102' olan çalışanın maaş artışlarını bul ('to_date' sütununu kullanarak).

     12. Find the employee with the highest salary
     - En yüksek maaşa sahip çalışanı bul.

     13. Find the latest salaries for each employee
     - Her çalışanın en son maaşlarını bul.

     14. List the first name, last name, and highest salary of employees in the "Sales" department.
     Order the list by highest salary descending and only show the employee with the highest salary.
     - "Satış" departmanındaki çalışanların adını, soyadını ve en yüksek maaşını listele.
     Listeyi en yüksek maaşa göre azalan şekilde sırala ve sadece en yüksek maaşa sahip çalışanı
     göster.

     15. Find the Employee with the Highest Salary Average in the Research Department
     - Araştırma Departmanındaki Ortalama En Yüksek Maaşlı Çalışanı Bul

     16. For each department, identify the employee with the highest single salary ever recorded. List the
     department name, employee's first name, last name, and the peak salary amount. Order the results
     by the peak salary in descending order.
     - Her departman için, kaydedilmiş en yüksek tek maaşı belirle. Departman adını, çalışanın adını,
     soyadını ve en yüksek maaş tutarını listele. Sonuçları en yüksek maaşa göre azalan şekilde sırala.

     17. Identify the employees in each department who have the highest average salary. List the
     department name, employee's first name, last name, and the average salary. Order the results by
     average salary in descending order, showing only those with the highest average salary within their
     department.
     - Her departmandaki en yüksek ortalama maaşa sahip çalışanları belirle. Departman adını,
     çalışanın adını, soyadını ve ortalama maaşı listele. Sonuçları departmanlarına göre azalan şekilde
     sırala, sadece kendi departmanlarında en yüksek ortalama maaşa sahip olanları göster.

     18. List the names, last names, and hire dates in alphabetical order of all employees hired before
     January 01, 1990.
     - 1990-01-01 tarihinden önce işe alınan tüm çalışanların adlarını, soyadlarını ve işe alınma
     tarihlerini alfabetik sırayla listele.

     19. List the names, last names, hire dates of all employees hired between January 01, 1985 and
     December 31, 1989, sorted by hire date.
     - 1985-01-01 ile 1989-12-31 tarihleri arasında işe alınan tüm çalışanların adlarını, soyadlarını ve işe
     alınma tarihlerini işe alınma tarihine göre sıralı olarak listele.

     20. List the names, last names, hire dates, and salaries of all employees in the Sales department who
     were hired between January 01, 1985 and December 31, 1989, sorted by salary in descending order.
     - 1985-01-01 ile 1989-12-31 tarihleri arasında işe alınan Satış departmanındaki tüm çalışanların
     adlarını, soyadlarını, işe alınma tarihlerini ve maaşlarını, maaşa göre azalan şekilde sıralı olarak
     listele.

     21.
     -- a: Find the count of male employees (179973)
     -- Erkek çalışanların sayısını bulun (179973)
     -- b: Determine the count of female employees (120050)
     -- Kadın çalışanların sayısını belirleyin (120050)
     -- c: Find the number of male and female employees by grouping:
     -- Gruplandırarak erkek ve kadın çalışanların sayısını bulun:
     -- d: Calculate the total number of employees in the company (300023)
     -- Şirketteki toplam çalışan sayısını hesaplayın (300023)

     22.
     -- a: Find out how many employees have unique first names (1275)
     -- Kaç çalışanın benzersiz ilk adı olduğunu bulun (1275)
     -- b: Identify the number of distinct department names (9)
     -- Farklı bölüm adlarının sayısını belirleyin (9)

     23. List the number of employees in each department
     -- Her bölümdeki çalışan sayısını listele

     24. List all employees hired within the last 5 years from February 20, 1990
     -- 1990-02-20 tarihinden sonraki son 5 yıl içinde işe alınan tüm çalışanları listele

     25. List the information (employee number, date of birth, first name, last name, gender, hire date) of
     the employee named "Annemarie Redmiles".
     -- "Annemarie Redmiles" adlı çalışanın bilgilerini (çalışan numarası, doğum tarihi, ilk adı, soyadı,
     cinsiyet, işe alınma tarihi) listele.

     26. List all information (employee number, date of birth, first name, last name, gender, hire date,
     salary, department, and title) for the employee named "Annemarie Redmiles".
     -- "Annemarie Redmiles" adlı çalışanın tüm bilgilerini (çalışan numarası, doğum tarihi, ilk adı,
     soyadı, cinsiyet, işe alınma tarihi, maaş, departman ve unvan) listele.

     27. List all employees and managers in the D005 department
     -- D005 bölümündeki tüm çalışanları ve yöneticileri listele

     28. List all employees hired after '1994-02-24' and earning more than 50,000
     -- '1994-02-24' tarihinden sonra işe alınan ve 50.000'den fazla kazanan tüm çalışanları listele


     29. List all employees working in the "Sales" department with the title "Manager"
     -- "Satış" bölümünde "Yönetici" unvanıyla çalışan tüm çalışanları listele

     30. Find the department where employee with '10102' has worked the longest
     -- '10102' numaralı çalışanın en uzun süre çalıştığı departmanı bul

     31. Find the highest paid employee in department D004
     -- D004 bölümünde en yüksek maaş alan çalışanı bulun

     32. Find the entire position history for employee with emp. no '10102'
     -- '10102' numaralı çalışanın tüm pozisyon geçmişini bulun

     33. Finding the average "employee age"
     -- Ortalama "çalışan yaşını" bulma

     34. Finding the number of employees per department
     -- Bölüm başına çalışan sayısını bulma

     35. Finding the managerial history of employee with ID (emp. no) 110022
     -- 110022 numaralı çalışanın yönetim geçmişini bulma

     36. Find the duration of employment for each employee
     -- Her çalışanın istihdam süresini bulma

     37. Find the latest title information for each employee
     -- Her çalışanın en son unvan bilgisini bulma

     38. Find the first and last names of managers in department 'D005'
     -- 'D005' bölümünde yöneticilerin adını ve soyadını bulma

     39. Sort employees by their birth dates
     -- Çalışanları doğum tarihlerine göre sıralama

     40. List employees hired in April 1992
     -- Nisan 1992'de işe alınan çalışanları listeleme

     41. Find all departments that employee '10102' has worked in.
     -- '10102' numaralı çalışanın çalıştığı tüm bölümleri bulma.
     **/


}
