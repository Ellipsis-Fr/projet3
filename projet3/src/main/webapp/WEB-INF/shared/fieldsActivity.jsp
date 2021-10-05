<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-6">
	<form:label for="name" path="name" class="form-label">Nom de l'Activité :<span style="color: red">*</span></form:label>
	<form:input type="text" path="name" class="form-control" id="name" name="name" pattern="[a-zA-Z-\\s]{3,15}" maxlength="15" required="required"/>
	<span class="error"></span>
</div>

<div class="col-md-6">
	<form:label for="address" path="address" class="form-label" >Adresse :<span style="color: red">*</span></form:label>
	<form:input type="text" path="address" class="form-control" id="address" name="address" pattern="\d+\s+(?:bis?\s|ter?\s)?(?:rue?\s|avenue\s|boulevard?\s)([\sa-zA-Z\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc])+\s[0-9]{5}\s[\sa-zA-Z\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc -]+" placeholder="123 rue de la fleur 45678 Paris" maxlength="50" required="required"/>
	<span class="error"></span>
</div>

<div class="col-md-6">
	<label for="startDate" class="form-label" >Date de début :<span style="color: red">*</span></label>
	<input name="startDate" value="${activity.startDate}" type="date" class="form-control" placeholder="date de debut" required="required"/>
	<span class="error"></span>
</div>

<div class="col-md-6">
	<label for="endDate" class="form-label">Date de fin :<span style="color: red">*</span></label>
	<input name="endDate" value="${activity.endDate}" type="date" class="form-control" placeholder="date de fin" required="required"/>
	<span class="error"></span>
</div>

<div class="col-md-6">
	<form:label for="description" path="description" class="form-label">Description :<span style="color: red">*</span></form:label>
	<form:input type="text" path="description" class="form-control" id="description" name="description" placeholder="Description de l'activité" required="required"/>
	<span class="error"></span>
</div>

<div class="col-md-6">
	<label for="photo" class="form-label">Photo</label>
	<input type="file" class="form-control" id="photo" name="photo" accept=".jpg,.jpeg,.png"/>
	<span class="error"></span>
</div>

<div class="col-md-6">
	<form:label for="necessaryFunding" path="necessaryFunding" class="form-label">Fond nécessaire pour l'activité :<span style="color: red">*</span></form:label>
	<form:input type="text" path="necessaryFunding" class="form-control" id="necessaryFunding" name="necessaryFunding" pattern="^\d+$" required="required"/>
	<span class="error"></span>
</div>

<div class="col-md-6">
	<form:label for="volunteerNeeded" path="volunteerNeeded" class="form-label">Bénévole(s) nécessaire(s) pour l'activité :<span style="color: red">*</span></form:label>
	<form:input type="text" path="volunteerNeeded" class="form-control" id="volunteerNeeded" name="volunteerNeeded" pattern="^\d+$" required="required"/>
	<span class="error"></span>
</div>

<div class="col-md-6">
	<div class="form-check form-check-inline">
	<label for="withParticipant">Avec participant</label>
	<input class="form-check-input" type="radio" name="category" id="withParticipant" value="0" required="required"> 
	</div>
<div class="form-check form-check-inline">
	<label	for="withoutParticipant">Sans participant</label>
	<input class="form-check-input" type="radio" name="category" id="withoutParticipant" value="1">
</div>
</div>
<div>
<input id="id" name="id" type="hidden" value="${activity.id}"/>

</div>