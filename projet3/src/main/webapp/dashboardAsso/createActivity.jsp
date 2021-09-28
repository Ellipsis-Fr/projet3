<%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Création d'une activité</title>
</head>
<body>
    
	<div class="main">
		<div class="container">
			<div class="signup-form">
				<form method="POST"
					class="register-form" id="formActivityRegistration"
					action="registrationActivity" enctype="multipart/form-data">
					<h2>Création Activité</h2>

					<div class="form-row">
						<div class="form-group">
							<label for="name">Nom Activité :<span
									style="color: red">*</span>
							</label>
							<input type="text" name="name" id="name" required="required" />
							<span class="error"></span>
						</div>
						<div class="form-group">
							<label for="address">Adresse <span
									style="color: red">*</span>
							</label>
							<input type="text" name="address" id="address"
								pattern="\d+\s+(?:bis?\s|ter?\s)?(?:rue?\s|avenue\s|boulevard?\s)([\sa-zA-Z\\u00e0\\u00e2\\u00e4\\u00e7\\u00e8\\u00e9\\u00ea\\u00ee\\u00ef\\u00f4\\u00f6\\u00f9\\u00fb\\u00fc])+\s[0-9]{5}\s[\sa-zA-Z\\u00e0\\u00e2\\u00e4\\u00e7\\u00e8\\u00e9\\u00ea\\u00ee\\u00ef\\u00f4\\u00f6\\u00f9\\u00fb\\u00fc -]+"
								placeholder="123 rue de la fleur 45678 Paris"
								required="required" />
							<span class="error"></span>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<input name="startDate" type="date" class="form-control"
								placeholder="date de debut" required="required" />
						</div>
						<div class="col">
							<input name="endDate" type="date" class="form-control"
								placeholder="date de fin" required="required" />
<!-- 								 <input	type="datetime-local" class="form-control" id="endDate" name="endDate" value="2018-06-12T19:30" min="2021-10-08T00:00" max="2022-12-31T00:00" required="required"> -->
						</div> 
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="description">Description :</label>
							<input type="text" name="description" id="description"
								placeholder="Description de l'activité" />
						</div>

						<div class="form-group">
							<label for="photo">Photo : <span style="color: red">*</span></label>
							<input type="file" name="photo" id="photo"
								accept=".jpg,.jpeg,.png" required />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="necessaryFunding">Fond nécessaire <span
									style="color: red">*</span>
							</label>
							<input type="text" name="necessaryFunding" pattern="\d{1,3}$"
								required="required" />
						</div>

						<div class="form-group">
							<label for="volunteerNeeded">Bénévole nécessaire <span
									style="color: red">*</span>
							</label>
							<input type="text" name="volunteerNeeded" pattern="\d{1,2}$"
								required="required" />
							<span class="error"></span>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="category"
									id="withParticipant" value="0" required> <label
									for="withParticipant">Avec participant</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="category"
									id="withoutParticipant" value="1" required> <label
									for="withoutParticipant">Sans participant</label>
							</div>
							
						</div>
					</div>

					<div class="form-submit">
						<input type="reset" value="Réinitialiser" class="submit"
							name="Reset All" id="reset" /> <input type="submit"
							value="Valider" class="submit" name="submit" id="submit" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>