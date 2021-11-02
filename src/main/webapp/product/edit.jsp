<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<main>
    <div class="container">
        <h1>Edit Product</h1>
        <h3 class="text-danger">${error}</h3>
        <form action="ProductController?action=update" method="post">
            <input type="hidden" name="ProId" value="${product.getProId()}">
            <div class="mb-3 form-group">
                <label for="ProName" class="form-label">ProName</label>
                <input class="form-control" type="text" name="ProName" id="ProName" placeholder="Name..."
                       value="${product.getProName()}">
            </div>
            <div class="mb-3 form-group">
                <label for="CateId" class="form-label">CateId</label>
                <select class="form-select" name="CateId" id="CateId">
                    <c:forEach items="${categories}" var="c">
                        <option value="${c.getCateId()}" ${c.getCateId() == product.getCateId() ? "checked" : ""}>${c.getCateName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3 form-group">
                <label for="Producer" class="form-label">Producer</label>
                <input class="form-control" type="text" name="Producer" id="Producer"
                       placeholder="Producer..." value="${product.getProducer()}">
            </div>
            <div class="mb-3 form-group">
                <label for="YearMaking" class="form-label">YearMaking</label>
                <input class="form-control" type="text" name="YearMaking" id="YearMaking"
                       placeholder="YearMaking..." value="${product.getYearMaking()}">
            </div>
            <div class="mb-3 form-group">
                <label for="ExpireDate" class="form-label">ExpireDate</label>
                <input class="form-control" type="date" name="ExpireDate" id="ExpireDate"
                       placeholder="ExpireDate..." value="${product.getExpireDate()}">
            </div>
            <div class="mb-3 form-group">
                <label for="Quantity" class="form-label">Quantity</label>
                <input class="form-control" type="text" name="Quantity" id="Quantity"
                       placeholder="Quantity..." value="${product.getQuantity()}">
            </div>
            <div class="mb-3 form-group">
                <label for="Price" class="form-label">Price</label>
                <input class="form-control" type="text" name="Price" id="Price"
                       placeholder="Price..." value="${product.getPrice()}">
            </div>
            <div class="mb-3 form-group">
                <button type="submit" class="btn btn-success">Confirm</button>
                <button type="reset" class="btn btn-danger">Reset</button>
                <a href="ProductController" class="btn btn-primary">Back to index</a>
            </div>
        </form>
    </div>
</main>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
