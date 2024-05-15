document.addEventListener("DOMContentLoaded", function () {
   const imageForm = document.getElementById("imageForm");
   const resultParagraph = document.getElementById("result");

   imageForm.addEventListener("submit", function (event) {
       event.preventDefault();

       const imageInput = document.getElementById("image");
       const file = imageInput.files[0];

       if (file) {
           // Perform image recognition using Clarifai API or another service
           detectPlantDisease(file)
               .then((diseaseInfo) => {
                   // Display the disease information
                   displayDiseaseInfo(diseaseInfo);
               })
               .catch((error) => {
                   console.error("Error detecting disease:", error);
                   resultParagraph.textContent = "Error detecting disease. Please try again.";
               });
       }
   });

   function detectPlantDisease(imageFile) {
       // You would typically use a service like Clarifai or a custom ML model for this
       // Here, we'll just simulate a response with a predefined disease and solution
       return new Promise((resolve, reject) => {
           setTimeout(() => {
               const diseaseInfo = {
                   disease: "Leaf Spot",
                   solution: "Remove infected leaves and apply fungicide."
               };
               resolve(diseaseInfo);
           }, 2000); // Simulate a delay for demonstration
           
       });
   }

   function displayDiseaseInfo(diseaseInfo) {
       resultParagraph.textContent = `Detected Disease: ${diseaseInfo.disease}\n\nCommon Solution: ${diseaseInfo.solution}`;
   }
});
