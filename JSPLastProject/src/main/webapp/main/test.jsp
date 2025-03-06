<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>숙소 검색</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <!-- Left Sidebar -->
      <div class="col-md-3">
        <div class="p-3">
          <h4>숙소 필터</h4>
          <form>
            <!-- 버튼 예시 -->
            <button class="btn btn-primary mb-2 w-100">필터 1</button>
            <button class="btn btn-secondary mb-2 w-100">필터 2</button>

            <!-- 라디오 버튼 예시 -->
            <div class="form-check">
              <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
              <label class="form-check-label" for="exampleRadios1">
                가격 낮은 순
              </label>
            </div>
            <div class="form-check">
              <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="option2">
              <label class="form-check-label" for="exampleRadios2">
                가격 높은 순
              </label>
            </div>

            <!-- 범위 예시 -->
            <div class="mt-3">
              <label for="priceRange">가격 범위</label>
              <input type="range" class="form-range" id="priceRange" min="0" max="10000" step="100" value="5000">
              <p>₩5000 ~ ₩10000</p>
            </div>
          </form>
        </div>
      </div>

      <!-- Right Sidebar (Card 리스트) -->
      <div class="col-md-9">
        <div class="row">
          <!-- 숙소 카드 예시 -->
          <div class="col-md-4 mb-4">
            <div class="card">
              <img src="https://via.placeholder.com/150" class="card-img-top" alt="숙소 이미지">
              <div class="card-body">
                <h5 class="card-title">숙소 이름</h5>
                <p class="card-text">주소: 서울시 예시구 예시로 123</p>
                <p class="card-text">₩100,000/1박</p>
              </div>
            </div>
          </div>

          <!-- 추가 숙소 카드 예시 -->
          <div class="col-md-4 mb-4">
            <div class="card">
              <img src="https://via.placeholder.com/150" class="card-img-top" alt="숙소 이미지">
              <div class="card-body">
                <h5 class="card-title">숙소 이름</h5>
                <p class="card-text">주소: 부산시 예시구 예시로 456</p>
                <p class="card-text">₩150,000/1박</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


</body>
</html>