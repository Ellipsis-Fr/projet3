<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    <div class="main">
	        <div class="container">
				<form method="POST" id="formCreateEvent" action="createEvent">
              		<h2 style="color: black">Lancement Collecte</h2>
					<div class="row">
						<div class="col">
							<input name="startDate" type="date" class="form-control" placeholder="date de début" required="required"/>
						</div>
						<div class="col">
							<input name="endDate" type="date" class="form-control" placeholder="date de fin" required="required"/>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="typeEvent" id="funndraising" value="0" required>
								<label class="form-check-label" for="funndraising" style="color: black">Collecte</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="typeEvent" id="funndraisingAndActivities" value="1" required>
								<label class="form-check-label" for="funndraisingAndActivities" style="color: black">Collecte et Activités</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="typeEvent" id="raffle" value="2" required>
								<label class="form-check-label" for="raffle" style="color: black">Tombolla</label>
							</div>
						</div>
					</div>
                        
                    <div class="form-submit">
                        <input type="submit" value="Valider" class="btn btn-primary" name="submit" id="submit" />
                    </div>
                 </form>
  
	        </div>
    	</div>