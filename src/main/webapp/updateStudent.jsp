<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>StudentManagementApp - Payment</title>
    <link href="WEB-INF/css/style.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
            integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
            integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">SMApp</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 myNavBar">
                    <li class="nav-item">
                        <a class="nav-link " aria-current="page" href="#">Home Page</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Students</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Teachers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Subjects</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Groups</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Payments</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

</div>
<div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingTwo">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
            Update Student
        </button>
    </h2>
    <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo"
         data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">
            <div class="container-fluid">
                <form class="row g-3">
                    <div class="col-md-6">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" name="name" id="name">
                    </div>
                    <div class="col-md-6">
                        <label for="surname" class="form-label">Surname</label>
                        <input type="text" class="form-control" name="surname" id="surname">
                    </div>
                    <div class="col-md-6">
                        <label for="dob" class="form-label">DOB</label>
                        <input type="date" class="form-control" name="dob" id="dob">
                    </div>
                    <div class="col-md-6">
                        <label for="serialNum" class="form-label">Serial Number</label>
                        <input type="text" class="form-control" name="serialNum" id="serialNum">
                    </div>
                    <div class="col-md-6">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" name="email" id="email">
                    </div>
                    <div class="col-md-6">
                        <label for="phone" class="form-label">Phone</label>
                        <input type="tel" class="form-control" name="phone" id="phone">
                    </div>

                    <div class="col-md-12" style="text-align: center">
                        <div class="form-check" style="display: inline-block; margin: 1%">
                            <input class="form-check-input" type="radio" name="genderRadio" id="female">
                            <label class="form-check-label" for="female">
                                Female
                            </label>
                        </div>
                        <div class="form-check" style="display: inline-block; margin: 1%">
                            <input class="form-check-input" type="radio" name="genderRadio" id="male">
                            <label class="form-check-label" for="male">
                                Male
                            </label>
                        </div>
                        <div class="form-check" style="display: inline-block; margin: 1%">
                            <input class="form-check-input" type="radio" name="genderRadio" id="other" checked>
                            <label class="form-check-label" for="other">
                                Other
                            </label>
                        </div>
                    </div>
                    <div class="col-12">
                    </div>
                    <div style="text-align: center" class="col-12">
                        <button type="submit" class="btn btn-success">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>