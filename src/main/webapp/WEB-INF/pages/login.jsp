<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>

<jsp:directive.page contentType="text/html;charset=UTF-8" />


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<!--     Temporary solution because of unresolved error: “Resource interpreted as script but transferred with MIME type text/html.” -->
    <style>
    
		body {
		  padding-top: 40px;
		  padding-bottom: 40px;
		  background-color: #eee;
		}
		
		.form-signin {
		  max-width: 330px;
		  padding: 15px;
		  margin: 0 auto;
		}
		
		.form-signin .form-signin-heading,
		.form-signin .checkbox {
		  margin-bottom: 10px;
		}
		
		.form-signin .checkbox {
		  font-weight: normal;
		}
		
		.form-signin .form-control {
		  position: relative;
		  height: auto;
		  -webkit-box-sizing: border-box;
		     -moz-box-sizing: border-box;
		          box-sizing: border-box;
		  padding: 10px;
		  font-size: 16px;
		}
		
		.form-signin .form-control:focus {
		  z-index: 2;
		}
		
		.form-signin input[type="email"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
		
		.form-signin input[type="password"] {
		  margin-bottom: 10px;
		  border-top-left-radius: 0;
		  border-top-right-radius: 0;
		}
		
		  .btn > .caret,
		  .dropup > .btn > .caret {
		    border-top-color: #000 !important;
		  }
		  .btn {
		  display: inline-block;
		  padding: 6px 12px;
		  margin-bottom: 0;
		  font-size: 14px;
		  font-weight: normal;
		  line-height: 1.42857143;
		  text-align: center;
		  white-space: nowrap;
		  vertical-align: middle;
		  cursor: pointer;
		  -webkit-user-select: none;
		     -moz-user-select: none;
		      -ms-user-select: none;
		          user-select: none;
		  background-image: none;
		  border: 1px solid transparent;
		  border-radius: 4px;
		}
		.btn:focus,
		.btn:active:focus,
		.btn.active:focus {
		  outline: thin dotted;
		  outline: 5px auto -webkit-focus-ring-color;
		  outline-offset: -2px;
		}
		.btn:hover,
		.btn:focus {
		  color: #333;
		  text-decoration: none;
		}
		.btn:active,
		.btn.active {
		  background-image: none;
		  outline: 0;
		  -webkit-box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
		          box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
		}
		.btn.disabled,
		.btn[disabled],
		fieldset[disabled] .btn {
		  pointer-events: none;
		  cursor: not-allowed;
		  filter: alpha(opacity=65);
		  -webkit-box-shadow: none;
		          box-shadow: none;
		  opacity: .65;
		}
		.btn-default {
		  color: #333;
		  background-color: #fff;
		  border-color: #ccc;
		}
		.btn-default:hover,
		.btn-default:focus,
		.btn-default:active,
		.btn-default.active,
		.open > .dropdown-toggle.btn-default {
		  color: #333;
		  background-color: #e6e6e6;
		  border-color: #adadad;
		}
		.btn-default:active,
		.btn-default.active,
		.open > .dropdown-toggle.btn-default {
		  background-image: none;
		}
		.btn-default.disabled,
		.btn-default[disabled],
		fieldset[disabled] .btn-default,
		.btn-default.disabled:hover,
		.btn-default[disabled]:hover,
		fieldset[disabled] .btn-default:hover,
		.btn-default.disabled:focus,
		.btn-default[disabled]:focus,
		fieldset[disabled] .btn-default:focus,
		.btn-default.disabled:active,
		.btn-default[disabled]:active,
		fieldset[disabled] .btn-default:active,
		.btn-default.disabled.active,
		.btn-default[disabled].active,
		fieldset[disabled] .btn-default.active {
		  background-color: #fff;
		  border-color: #ccc;
		}
		.btn-default .badge {
		  color: #fff;
		  background-color: #333;
		}
		.btn-primary {
		  color: #fff;
		  background-color: #428bca;
		  border-color: #357ebd;
		}
		.btn-primary:hover,
		.btn-primary:focus,
		.btn-primary:active,
		.btn-primary.active,
		.open > .dropdown-toggle.btn-primary {
		  color: #fff;
		  background-color: #3071a9;
		  border-color: #285e8e;
		}
		  	.btn-lg,
		.btn-group-lg > .btn {
		  padding: 10px 16px;
		  font-size: 18px;
		  line-height: 1.33;
		  border-radius: 6px;
		}
		
		.btn-lg,
		.btn-group-lg > .btn {
		  padding: 10px 16px;
		  font-size: 18px;
		  line-height: 1.33;
		  border-radius: 6px;
		}
		
		.btn-primary {
		  color: #fff;
		  background-color: #428bca;
		  border-color: #357ebd;
		}
		
		.btn-block {
		  display: block;
		  width: 100%;
		}
		.btn-block + .btn-block {
		  margin-top: 5px;
		}
		
		.form-control {
		  position: relative;
		  z-index: 2;
		  float: left;
		  width: 100%;
		  margin-bottom: 0;
		}
		
		.form-control {
		    display: inline-block;
		    width: auto;
		    vertical-align: middle;
		  }
    </style>
    <title>Bejelentkezés</title>

  </head>

  <body>
  		<c:if test="${not empty error}">
			<font color="red"> Bejelentkezési hiba <br /> 
				<div>Sajnos nem sikerült a bejelentkezés. A felhasználónév vagy a jelszó nem megfelelő.</div>
			</font>
		</c:if>
		<c:if test="${not empty msg}">
			<font color="red">  
				<div>Sikeres kijelentkezés!</div>
			</font>
		</c:if>
  

    <div class="container">

      <form class="form-signin" role="form" method="POST"
			action="<c:url value="/j_spring_security_check" />">
        <h2 class="form-signin-heading">Bejelentkezés</h2>
        <input type="text" class="form-control" name="j_username" placeholder="Felhasználónév" required autofocus>
        <input type="password" class="form-control" name="j_password" placeholder="Jelszó" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Bejelentkezés</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>
