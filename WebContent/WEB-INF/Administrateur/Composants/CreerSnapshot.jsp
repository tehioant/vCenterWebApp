<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 
 
 
<div class="card card-body z-depth-2" id="CardVM">
				    <h4 class="card-title">Creation Snapshot </h4>
				    <form action="CreateSnapshot" method="post" >
			<div class="panel panel-default" style="margin-top: 5px">
				<div class="panel-heading"
					style="background-image: linear-gradient(to bottom, #337ab7 0, #337ab7 100%); background-color: #337ab7; color: #f5f5f5">Informations
					VM </div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-5">
							<div class="form-group">
								<label for="name">Nom</label> <input type="text" id="name"
									name="name" >
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label for="name">Description : </label> <input type="text" id="description"
									name="description">
							</div>
						</div>
						<input type="hidden" id="description" value ="" name="description">
						</div>
						<div class="row">
							<div class="col-md-4 col-md-offset-4 ">
								<button class="btn btn-primary center-block" type="submit">Submit</button>
							</div>
						</div>
					</div>
				</div>
		</form>
    <div class="flex-row">
        <a class="card-link">Card link</a>
        <a class="card-link">Another link</a>
  	</div>
     		</div>


