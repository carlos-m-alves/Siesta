<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Hello, world!</title>
  </head>
  <body class="bg-warning py-5">
      <div class="col-md-6 offset-md-3">
          <div class="card">
              <div class="card-body">
                  <h1>Dynamic Chart <button class="btn btn-success" onclick="updateChart()">Update</button></h1>
                  <button class="btn btn-info" onclick="addValue()">+ 1</button>
              </div>
              <div class="card-body">
                  <canvas id="myChart"></canvas>
              </div>
          </div>
      </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script>
        var oldData = [0, 10, 5, 2, 20, 30, 45];
        var newData = [10, 20, 30, 40, 50, 60, 70];
        var oldData1 = [99, 0, 4, 20, 40, 20, 12];
        var newData1 = [2, 10, 40, 55, 30, 65, 17];
        
        var ctx = document.getElementById('myChart').getContext('2d');      
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',

            // The data for our dataset
            data: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                datasets: [{
                    label: 'My First dataset',
                    //backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: oldData
                },{
                    label: 'My Second dataset',
                    //backgroundColor: 'rgb(0, 200, 0)',
                    borderColor: 'rgb(0, 200, 0)',
                    data: oldData1
                }]
            },

            // Configuration options go here
            options: {}            
        });
        
        function updateChart(){
          //chart.data.datasets[0].data = newData;
          //chart.data.datasets[1].data = newData1;
          chart.data.labels = ["August","September","October"]
          chart.update();  
        };
        
        function addValue(){
          chart.data.labels.push("January");
          chart.update();  
        };
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>
