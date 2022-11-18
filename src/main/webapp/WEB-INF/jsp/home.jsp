<%@ page contentType="text/html; charset=utf-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<form method="get" action="/search">
    <h2>검색 범위</h2>
    <select name="mainCategory" onchange="categoryChange(this)">
        <option value="none" selected>전체</option>
        <option value="department">부서</option>
        <option value="sex">성별</option>
        <option value="salary">연봉</option>
        <option value="bdate">생일</option>
    </select>

    <select name="subCategory" id="category">
    </select>
    <input type="text" name="inputText">
    <script>
        function categoryChange(e) {
            var none = [];
            var department = ["Research", "Administration", "Headquarters"];
            var sex = ["M", "F"];
            var birthday = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
            var target = document.getElementById("category");
            var d;

            if (e.value == "department") d = department;
            else if (e.value == "sex") d = sex;
            else if (e.value == "bdate") d = birthday;
            else d = none;

            target.options.length = 0;

            for (x in d) {
                var opt = document.createElement("option");
                opt.value = d[x];
                opt.innerHTML = d[x];
                target.appendChild(opt);
            }
        }
    </script>
    <h2>검색 항목</h2>
    <label><input type="checkbox" name="name" value="1" checked> Name</label>
    <label><input type="checkbox" name="ssn" value="1" checked> Ssn</label>
    <label><input type="checkbox" name="bdate" value="1" checked> Bdate</label>
    <label><input type="checkbox" name="address" value="1" checked> Address</label>
    <label><input type="checkbox" name="sex" value="1" checked> Sex</label>
    <label><input type="checkbox" name="salary" value="1" checked> Salary</label>
    <label><input type="checkbox" name="supervisor" value="1" checked> Supervisor</label>
    <label><input type="checkbox" name="department" value="1" checked> Department</label>
    <p><input type="submit" value="검색"></p>
</form>
<p>
<form method="get" action="/search/family">
    <h2>직원별 가족 검색 (ssn 입력)</h2>
    <input type="text" name="ssn">
    <p><input type="submit" value="가족검색"></p>
</form>
</p>
<p>
<form method="post" action="/insert">
    <h2>새로운 직원 정보 추가</h2>
    <label>First Name : <input type="text" name="fName"></label><br>
    <label>Middle Init : <input type="text" name="mInit"></label><br>
    <label>Last Name : <input type="text" name="lName"></label><br>
    <label>Ssn : <input type="text" name="ssn"></label><br>
    <label>Birthdate : <input type="text" name="bDate"></label><br>
    <label>Address : <input type="text" name="address"></label><br>
    <label>Sex : <input type="text" name="sex"></label><br>
    <label>Salary : <input type="text" name="salary"></label><br>
    <label>Super_ssn : <input type="text" name="superSsn"></label><br>
    <label>Dno : <input type="text" name="dno"></label><br>
    <p><input type="submit" value="정보 추가하기"></p>
</form>
<p>
<h2>검색 결과</h2>
</p>
</body>
</html>