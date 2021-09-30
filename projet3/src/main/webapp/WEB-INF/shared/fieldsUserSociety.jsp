<c:choose>
	<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'partner'}">
		<div class="col-md-6 fieldsSociety">
		<label for="companyName" class="form-label">Raison Sociale</label>
		<input type="text" class="form-control" id="companyName" name="companyName" value='<c:out value="${sessionScope.sessionUserLogged.companyName}"/>' maxlength="30" required />
		<span class="error"></span>
		</div>
		
		<div class="col-md-6 fieldsSociety">
		<label for="siret" class="form-label">Siret</label>
		<input type="text" class="form-control" id="siret" name="siret" pattern="[0-9]{14}" value='<c:out value="${sessionScope.sessionUserLogged.siret}"/>' placeholder="14 chiffres" maxlength="14" required />
		<span class="error"></span>
		</div>
	</c:when>
	<c:otherwise>
		<div class="col-md-6 fieldsSociety">
			<label for="companyName" class="form-label">Raison Sociale</label>
			<input type="text" class="form-control" id="companyName" name="companyName"  maxlength="30" required />
			<span class="error"></span>
		</div>
		
		<div class="col-md-6 fieldsSociety">
			<label for="siret" class="form-label">Siret</label>
			<input type="text" class="form-control" id="siret" name="siret" pattern="[0-9]{14}" placeholder="14 chiffres" maxlength="14" required />
			<span class="error"></span>
		</div>
	</c:otherwise>
</c:choose>

