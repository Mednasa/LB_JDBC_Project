
# Employees Schema
This README file contains various SQL queries for a sample `employees` schema. The queries can be adapted to your database schema and needs.



[MySQL Employee Structure](https://dev.mysql.com/doc/employee/en/sakila-structure.html)
## Employees Table
| Column Name | Description      |
|-------------|------------------|
| emp_no      | Employee number  |
| first_name  | First name       |
| last_name   | Last name        |
| birth_date  | Birth date       |
| gender      | Gender           |
| hire_date   | Hire date        |

## Departments Table
| Column Name | Description        |
|-------------|--------------------|
| dept_no     | Department number  |
| dept_name   | Department name    |

## Dept_Manager Table
| Column Name | Description                      |
|-------------|----------------------------------|
| emp_no      | Employee number                  |
| dept_no     | Department number                |
| from_date   | Start date of management         |
| to_date     | End date of management           |

## Titles Table
| Column Name | Description                       |
|-------------|-----------------------------------|
| emp_no      | Employee number                   |
| title       | Job title                         |
| from_date   | Start date of job title           |
| to_date     | End date of job title             |

## Salaries Table
| Column Name | Description                       |
|-------------|-----------------------------------|
| emp_no      | Employee number                   |
| salary      | Salary                            |
| from_date   | Start date of salary              |
| to_date     | End date of salary                |

![employees-schema.png](src/main/resources/img/employees-schema.png)

## Queries & Contributors

| Queries                                     | Contributor                  |
|------------------------------------------------|------------------------------|
| [29-30-31-32-33]| [Beyza Nur Er](https://github.com/beyzanurer) |
| [10-11-12-13-23-25]| [Ahmet Kaya](https://github.com/0AhmetKaya0)|
| [15-17-19-22-24-26] | [Onur Girgin](https://github.com/Mednasa) |
| [8-9-14-16-18-21] | [Nigar Ayla Özcanan](https://github.com/NigarAylaOzcanan) |
| [35-7-27-34-20-28]| [Numeyra](https://github.com/Numeyra) |
| [41-40-39-38-37-36] | [Erdem Gürel](https://github.com/artam109) |
| [1-2-3-4-5-6] | [Ömer Faruk Ayrıç](https://github.com/faruk-ayrcc25) |


## Team Members

| Name                    | Role            | GitHub                                           | 
|-------------------------|-----------------|--------------------------------------------------|
| Beyza Nur Er            | QA Engineer     | [Beyza Nur Er](https://github.com/beyzanurer)   | 
| Nigar Ayla Özcanan      | QA Engineer     | [Nigar Ayla Özcanan](https://github.com/NigarAylaOzcanan) | 
| Ahmet Kaya              | QA Engineer     | [Ahmet Kaya](https://github.com/0AhmetKaya0) | 
| Erdem Gürel             | QA Engineer     | [Erdem Gürel](https://github.com/artam109)       |    
| Ömer Faruk Ayrıç        | QA Engineer   | [Ömer Faruk Ayrıç](https://github.com/faruk-ayrcc25) | 
| Numeyra             | QA Engineer    | [Numeyra](https://github.com/Numeyra)  |
| Onur Girgin             | Team Lead & QA Engineer   | [Onur Girgin](https://github.com/Mednasa) |


##	4. Calculate the average salary of all employees with gender "`M`"
```SQL
SELECT AVG(s.salary) AS average_salary
FROM employees e
INNER JOIN salaries s ON e.emp_no = s.emp_no
WHERE e.gender = 'F';
```

##	5. Calculate the average salary of all employees with gender "`F`"
```SQL
SELECT AVG(s.salary) AS average_salary
FROM employees e
INNER JOIN salaries s ON e.emp_no = s.emp_no
WHERE e.gender = 'M';
```

##	6: List all employees in the "`sales`" department with a salary greater than `70,000`.
```SQL
SELECT e.*, s.salary
FROM employees e
JOIN dept_emp de ON e.emp_no = de.emp_no
JOIN departments d ON de.dept_no = d.dept_no
JOIN salaries s ON e.emp_no = s.emp_no
WHERE d.dept_name = 'Sales' AND s.salary > 70000;
```

##	7. This query retrieves employees who have salaries between `50000` and `100000`.
```SQL
SELECT e.*
FROM employees e
INNER JOIN salaries s ON e.emp_no = s.emp_no
WHERE s.salary BETWEEN 50000 AND 100000;
```

##	8. Calculate the average salary for each department (by department number or department name)
*   ###   by department no:
```SQL
SELECT de.dept_no, AVG(s.salary) AS average_salary
FROM dept_emp de
JOIN salaries s ON de.emp_no = s.emp_no
GROUP BY de.dept_no;
```

*   ###   by department name:
```SQL
SELECT dept_name, AVG(salary) AS avg_salary
FROM departments
JOIN dept_emp ON departments.dept_no = dept_emp.dept_no
JOIN salaries ON dept_emp.emp_no = salaries.emp_no
GROUP BY departments.dept_no;
```


##	9. Calculate the average salary for each department, including department names
```SQL
SELECT d.dept_no, d.dept_name, AVG(s.salary) AS average_salary
FROM employees e
JOIN dept_emp de ON e.emp_no = de.emp_no
JOIN salaries s ON e.emp_no = s.emp_no
JOIN departments d ON de.dept_no = d.dept_no
GROUP BY d.dept_no, d.dept_name;
```

##	10. Find all salary changes for employee with emp. no '`10102`':
```SQL
SELECT emp_no, salary, from_date, to_date
FROM salaries
WHERE emp_no = '10102'
ORDER BY from_date;
```

##	11. Find the salary increases for employee with employee number '`10102`' (using the `to_date` column in salaries):
```SQL
SELECT emp_no, salary, to_date
FROM salaries
WHERE emp_no = '10102'
ORDER BY to_date;
```

##	12. Find the employee with the highest salary:
```SQL
SELECT * FROM employees
JOIN salaries ON employees.emp_no = salaries.emp_no
ORDER BY salary DESC
LIMIT 1;
```

##	13. Find the latest salaries for each employee:
```SQL
SELECT emp_no, salary, to_date
FROM salaries
WHERE (emp_no, to_date) IN (SELECT emp_no, MAX(to_date) FROM salaries GROUP BY emp_no);
```

##	14. List the first name, last name, and highest salary of employees in the "`sales`" department. Order the list by highest salary descending and only show the employee with the highest salary.
```SQL
SELECT e.first_name, e.last_name, MAX(s.salary) AS highest_salary
FROM employees e
INNER JOIN dept_emp de ON e.emp_no = de.emp_no
INNER JOIN departments d ON de.dept_no = d.dept_no
INNER JOIN salaries s ON e.emp_no = s.emp_no
WHERE d.dept_name = 'Sales'
GROUP BY e.first_name, e.last_name
ORDER BY highest_salary DESC
LIMIT 1;
```

##	15. Find the Employee with the Highest Salary Average in the Research Department
```SQL
SELECT e.first_name, e.last_name, MAX(s.salary) AS max_salary
FROM employees e
INNER JOIN dept_emp de ON e.emp_no = de.emp_no
INNER JOIN departments d ON de.dept_no = d.dept_no
INNER JOIN salaries s ON e.emp_no = s.emp_no
WHERE d.dept_name = 'Research'
GROUP BY e.first_name, e.last_name
ORDER BY max_salary DESC
LIMIT 1;
```

##	16. For each department, identify the employee with the highest single salary ever recorded. List the department name, employee's first name, last name, and the peak salary amount. Order the results by the peak salary in descending order.
```SQL
SELECT d.dept_name AS department, e.first_name, e.last_name, MAX(s.salary) AS max_salary
FROM employees e
INNER JOIN dept_emp de ON e.emp_no = de.emp_no
INNER JOIN departments d ON de.dept_no = d.dept_no
INNER JOIN salaries s ON e.emp_no = s.emp_no
GROUP BY d.dept_name
ORDER BY max_salary DESC;
```

##	17. Identify the employees in each department who have the highest average salary. List the department name, employee's first name, last name, and the average salary. Order the results by average salary in descending order, showing only those with the highest average salary within their department.
```SQL
SELECT dept_name, first_name, last_name, average_salary
FROM (
    SELECT d.dept_name, e.first_name, e.last_name, AVG(s.salary) AS average_salary
    FROM departments d
    JOIN dept_emp de ON d.dept_no = de.dept_no
    JOIN employees e ON e.emp_no = de.emp_no
    JOIN salaries s ON s.emp_no = e.emp_no
    GROUP BY e.emp_no
    ORDER BY average_salary DESC
) AS new_table
GROUP BY dept_name
ORDER BY MAX(average_salary) DESC;
```

##	18. List the names, last names, and hire dates in alphabetical order of all employees hired before `January 01, 1990`.
```SQL
SELECT first_name, last_name, hire_date
FROM employees
WHERE hire_date < '1990-01-01'
ORDER BY first_name ASC, last_name ASC;
```

##	19. List the names, last names, hire dates of all employees hired between `January 01, 1985` and `December 31, 1989`, sorted by hire date.
```SQL
SELECT first_name, last_name, hire_date
FROM employees
WHERE hire_date BETWEEN '1985-01-01' AND '1989-12-31'
ORDER BY hire_date ASC;
```


##	20. List the names, last names, hire dates, and salaries of all employees in the Sales department who were hired between January 01, 1985 and December 31, 1989, sorted by salary in descending order.
```SQL
SELECT first_name, last_name, hire_date, salary
FROM employees
WHERE hire_date BETWEEN '1985-01-01' AND '1989-12-31'
AND department = 'Sales'
AND emp_no IN (SELECT emp_no FROM salaries)
ORDER BY salary DESC;
```

##	21.
*   ###   a: Find the count of male employees (`179973`)
```SQL
SELECT COUNT(gender) AS Male_Count FROM employees WHERE gender = 'M';
```
*   ###   b: Determine the count of female employees (`120050`)
```SQL
SELECT COUNT(gender) AS Female_Count FROM employees WHERE gender = 'F';
```
*   ###   c: Find the number of male and female employees by grouping:
```SQL
SELECT gender, COUNT(*) AS count FROM employees GROUP BY gender;
```
*   ###   d: Calculate the total number of employees in the company (`300023`)
```SQL
SELECT COUNT(emp_no) AS Total_Employees FROM employees;
```

##	22.
*   ###   a: Find out how many employees have unique first names (`1275`)
```SQL
SELECT COUNT(DISTINCT first_name) AS Unique_Names FROM employees;
```
*   ###   b: Identify the number of distinct department names (`9`)
```SQL
SELECT COUNT(DISTINCT dept_name) AS Unique_Departments FROM departments;
```

##	23. List the number of employees in each department
```SQL
SELECT de.dept_no, COUNT(*) AS employee_count
FROM dept_emp de
GROUP BY de.dept_no;
```

##	24. List all employees hired within the `last 5 years` from `February 20, 1990`
```SQL
SELECT *
FROM employees
WHERE hire_date <= DATE_SUB('1990-02-20', INTERVAL 5 YEAR);
```

##	25. List the information (employee number, date of birth, first name, last name, gender, hire date) of the employee named "`Annemarie Redmiles`".
```SQL
SELECT *
FROM employees
WHERE first_name = 'Annemarie' AND last_name = 'Redmiles';
```

##	26.	List all information (employee number, date of birth, first name, last name, gender, hire date, salary, department, and title) for the employee named "`Annemarie Redmiles`".
```SQL
SELECT *
FROM employees e
INNER JOIN salaries s ON e.emp_no = s.emp_no
INNER JOIN titles t ON e.emp_no = t.emp_no
WHERE e.first_name = 'Annemarie' AND e.last_name = 'Redmiles';
```

##  27. List all employees and managers in the `D005` department
```SQL
SELECT e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date, d.dept_name AS department_name, t.title, s.salary
FROM employees e
JOIN dept_manager dm ON e.emp_no = dm.emp_no
JOIN departments d ON dm.dept_no = d.dept_no
JOIN titles t ON e.emp_no = t.emp_no
JOIN salaries s ON e.emp_no = s.emp_no
WHERE d.dept_no = 'D005';
```

##	28. List all employees hired after '`1994-02-24`' and earning more than `50,000`
```SQL
SELECT e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date, t.title, s.salary
FROM employees e
JOIN titles t ON e.emp_no = t.emp_no
JOIN salaries s ON e.emp_no = s.emp_no
WHERE e.hire_date > '1994-02-24' AND s.salary > 50000;
```

##	29. List all employees working in the "`sales`" department with the title "`Manager`"
```SQL
SELECT e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date
FROM employees e
JOIN dept_manager dm ON e.emp_no = dm.emp_no
JOIN departments d ON dm.dept_no = d.dept_no
JOIN titles t ON e.emp_no = t.emp_no
WHERE d.dept_name = 'Sales' AND t.title = 'Manager';
```

##	30. Find the department where employee with '`10102`' has worked the longest
```SQL
SELECT employees.emp_no, departments.dept_name, DATEDIFF(MAX(dept_emp.to_date), MIN(dept_emp.from_date)) AS work_duration
FROM employees
JOIN dept_emp ON employees.emp_no = dept_emp.emp_no
JOIN departments ON dept_emp.dept_no = departments.dept_no
GROUP BY employees.emp_no
ORDER BY work_duration DESC
LIMIT 1;
```

##	31. Find the highest paid employee in department `D004`:
```SQL
SELECT employees.first_name, employees.last_name, dept_emp.dept_no, salaries.salary
FROM employees
JOIN salaries ON employees.emp_no = salaries.emp_no
JOIN dept_emp ON employees.emp_no = dept_emp.emp_no
WHERE salaries.salary = (
    SELECT max(salaries.salary) as max_salary
    FROM employees
    JOIN salaries ON employees.emp_no = salaries.emp_no
    JOIN dept_emp ON employees.emp_no = dept_emp.emp_no
    WHERE dept_emp.dept_no = 'D004'
) AND dept_emp.dept_no = 'D004';
```

##	32. Find the entire position history for employee with emp. no '`10102`':
```SQL
SELECT emp_no, title, from_date, to_date
FROM titles
WHERE emp_no = '10102'
ORDER BY from_date;
```

##	33. Finding the average "`employee age`":
```SQL
SELECT AVG(DATEDIFF(CURRENT_DATE, birth_date) / 365) AS avg_age FROM employees;
```

##	34. Finding the number of employees per department:
```SQL
SELECT dept_no, COUNT(*) AS employee_count
FROM dept_emp
GROUP BY dept_no;
```

##	35. Finding the managerial history of employee with ID (emp. no) `110022`:
```SQL
SELECT employees.first_name, employees.last_name, dept_manager.from_date, dept_manager.to_date
FROM employees
JOIN dept_manager ON employees.emp_no = dept_manager.emp_no
WHERE employees.emp_no = '110022';
```

##	36. Find the duration of employment for each employee:
```SQL
SELECT emp_no, DATEDIFF(MAX(to_date), MIN(from_date)) AS work_duration
FROM titles
GROUP BY emp_no;
```
_with name:_
```SQL
SELECT e.emp_no, e.first_name, e.last_name, DATEDIFF(MAX(t.to_date), MIN(t.from_date)) AS work_duration
FROM employees e
JOIN titles t ON e.emp_no = t.emp_no
GROUP BY e.emp_no, e.first_name, e.last_name
LIMIT 1000;
```

##	37. Find the latest title information for each employee:
```SQL
SELECT employees.emp_no, employees.first_name, employees.last_name, titles.title
FROM employees
JOIN titles ON employees.emp_no = titles.emp_no
WHERE titles.to_date = (SELECT MAX(to_date) FROM titles WHERE titles.emp_no = employees.emp_no);
```

##	38. Find the first and last names of managers in department '`D005`':
```SQL
SELECT employees.first_name, employees.last_name
FROM employees
JOIN dept_manager ON employees.emp_no = dept_manager.emp_no
WHERE dept_manager.dept_no = 'D005';
```

##	39. Sort employees by their birth dates:
```SQL
SELECT *  FROM employees ORDER BY birth_date;
```

##	40. List employees hired in `April 1992`:
```SQL
SELECT * FROM employees
WHERE hire_date BETWEEN '1992-04-01' AND '1992-04-30';
```

##	41. Find all departments that employee '`10102`' has worked in:
```SQL
SELECT DISTINCT dept_no
FROM dept_emp
WHERE emp_no = '10102';
```

# Project Structure :
```bash
SQLProject
│   pom.xml
│   Readme.md
├───src
│   ├───main
│   │   ├───java
│   │   └───resources
│   │       │   BaseQueries.sql
│   │       │   TaskAnswers.sql
│   │       │
│   │       └───img
│   │               employees-schema.png
│   └───test
│       └───java
│               DatabaseHelper.java
│               Queries.java
│               SampleJDBC.java

```
