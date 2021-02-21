const API_URL = "http://localhost:8080";

const ImageProcessorService = {
    
    processImages(images) {        
        const formData = new FormData()

        images.forEach((image, i) => {
          formData.append("images", image)
        })

        fetch(`${API_URL}/process-images`, {
          method: 'POST',
          body: formData
        })
        .then(res => res.json())
        .then(data => {
          localStorage.setItem("jobId", data.jobId);
          console.log(localStorage.getItem("jobId"));
        })
    }
}

export default ImageProcessorService;