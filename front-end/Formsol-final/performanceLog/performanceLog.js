document.addEventListener("DOMContentLoaded", function () {
   const formContainer = document.getElementById("formContainer");
   const showFormButton = document.getElementById("showFormButton");
   const cropForm = document.getElementById("cropForm");
   const cropDataTableBody = document.getElementById("cropDataBody");

   showFormButton.addEventListener("click", function () {
       // Show the form when the "Add Crop Data" button is clicked
       formContainer.style.display = "block";
   });

   cropForm.addEventListener("submit", function (event) {
       event.preventDefault();
       
       const cropName = document.getElementById("cropName").value;
       const expenditure = parseFloat(document.getElementById("expenditure").value);
       const revenue = parseFloat(document.getElementById("revenue").value);
       const profit = revenue - expenditure;
       const isWorthIt = profit > 0;

       // Display the result
       displayResult(isWorthIt);

       // Add the data to the table
       addToCropTable(cropName, expenditure, revenue, profit, isWorthIt);

       // Reset the form
       cropForm.reset();

       // Hide the form again
       formContainer.style.display = "none";
   });

   function displayResult(isWorthIt) {
       const resultDiv = document.getElementById("result");
       resultDiv.innerHTML = isWorthIt
           ? `<p>This crop is worth it!</p>`
           : `<p>This crop is not worth it.</p>`;
   }

   function addToCropTable(cropName, expenditure, revenue, profit, isWorthIt) {
       const row = document.createElement("tr");
       row.innerHTML = `
           <td>${cropName}</td>
           <td>${expenditure}</td>
           <td>${revenue}</td>
           <td>${profit}</td>
           <td>${isWorthIt ? "Yes" : "No"}</td>
       `;
       cropDataTableBody.appendChild(row);
   }
});
