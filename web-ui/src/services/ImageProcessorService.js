const API_URL = window.REACT_APP_API_URL;

const ImageProcessorService = {
    
    processImages(images) {        
        console.log(API_URL);
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