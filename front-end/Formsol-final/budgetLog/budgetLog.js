document.addEventListener("DOMContentLoaded", function () {
   const formContainer = document.getElementById("formContainer");
   const showFormButton = document.getElementById("showFormButton");
   const form = document.getElementById("worthForm");
   const dataTableBody = document.getElementById("dataBody");

   showFormButton.addEventListener("click", function () {
       // Show the form when the "Add" button is clicked
       formContainer.style.display = "block";
   });

   form.addEventListener("submit", function (event) {
       event.preventDefault();
       
       const amount = parseFloat(document.getElementById("amount").value);
       const comments = document.getElementById("comments").value;
       const profit = parseFloat(document.getElementById("profit").value);

       const isWorthIt = calculateWorthiness(amount, profit);

       // Display the result
       displayResult(isWorthIt, comments);

       // Add the data to the table
       addToTable(amount, comments, profit, isWorthIt);

       // Reset the form
       form.reset();

       // Hide the form again
       formContainer.style.display = "none";
   });

   function calculateWorthiness(amount, profit) {
       return profit > 0;
   }

   function displayResult(isWorthIt, comments) {
       const resultDiv = document.getElementById("result");
       resultDiv.innerHTML = isWorthIt
           ? `<p>It's worth it! ${comments}</p>`
           : `<p>It's not worth it. ${comments}</p>`;
   }

   function addToTable(amount, comments, profit, isWorthIt) {
       const row = document.createElement("tr");
       row.innerHTML = `
           <td>${amount}</td>
           <td>${comments}</td>
           <td>${profit}</td>
           <td>${isWorthIt ? "Yes" : "No"}</td>
       `;
       dataTableBody.appendChild(row);
   }
});
