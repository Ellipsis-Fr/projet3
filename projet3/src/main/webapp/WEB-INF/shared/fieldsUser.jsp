<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-6 fieldsUsers">
	<label for="lastname" class="form-label">Nom </label>
	<input type="text" class="form-control" id="lastname" name="lastname" pattern="[a-zA-Z-\\s]{3,15}" value='<c:out value="${sessionScope.sessionUserLogged.lastname}"/>' maxlength="15" required />
	<span class="error"></span>
</div>

<div class="col-md-6 fieldsUsers">
	<label for="firstname" class="form-label" >Prénom</label>
	<input type="text" class="form-control" id="firstname" name="firstname" pattern="[a-zA-Z-\s\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc]{3,15}" value='<c:out value="${sessionScope.sessionUserLogged.firstname}"/>' maxlength="15" required />
	<span class="error"></span>
</div>

<div class="col-md-6 fieldsUsers">
	<label for="address" class="form-label" >Adresse</label>
	<input type="text" class="form-control" id="address" name="address" pattern="\d+\s+(?:bis?\s|ter?\s)?(?:rue?\s|avenue\s|boulevard?\s)([\sa-zA-Z\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc])+\s[0-9]{5}\s[\sa-zA-Z\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc -]+" value='<c:out value="${sessionScope.sessionUserLogged.address}"/>' placeholder="123 rue de la fleur 45678 Paris" maxlength="50" required />
	<span class="error"></span>
</div>

<div class="col-md-6 fieldsUsers">
	<label for="telephone" class="form-label" >Numéro de téléphone</label>
	<input type="text" class="form-control" id="telephone" name="telephone" pattern="0[1-9][0-9]{8}" value='<c:out value="${sessionScope.sessionUserLogged.telephone}"/>' maxlength="10" />
	<span class="error"></span>
</div>

<div class="col-md-6 fieldsUsers">
	<label for="email" class="form-label">Email</label>
	<input type="email" class="form-control" id="email" name="email" value='<c:out value="${sessionScope.sessionUserLogged.email}"/>' maxlength="60" required/>
	<span class="error"></span>
</div>