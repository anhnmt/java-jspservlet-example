<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<main>
    <div class="container">
        <h1>Edit Category</h1>
        <h3 class="text-danger">${error}</h3>
        <form action="CategoryController?action=update" method="post">
            <input type="hidden" name="CateId" value="${category.getCateId()}">
            <div class="mb-3 form-group">
                <label for="CateName" class="form-label">Name</label>
                <input class="form-control" type="text" name="CateName" id="CateName" placeholder="Name..."
                       value="${category.getCateName()}">
            </div>
            <div class="mb-3 form-group">
                <label for="Description" class="form-label">Description</label>
                <input class="form-control" type="text" name="Description" id="Description"
                       placeholder="Description..." value="${category.getDescription()}">
            </div>
            <div class="mb-3 form-group">
                <button type="submit" class="btn btn-success">Confirm</button>
                <button type="reset" class="btn btn-danger">Reset</button>
                <a href="CategoryController" class="btn btn-primary">Back to index</a>
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
