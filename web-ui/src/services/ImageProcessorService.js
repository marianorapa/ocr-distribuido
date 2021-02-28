const axios = require('axios').default;

const API_URL = window.REACT_APP_API_URL;

const ImageProcessorService = {
    
    processImages(images) {              
      const formData = new FormData()
      images.forEach((image, i) => {        
        formData.append("images", image)
      })
      
      axios.post(`${API_URL}/process-images`,  formData)
        .then(res => res.data)
        .then(data => {
          localStorage.setItem("jobId", data.jobId);
        })
    },

    /**
     * Returns a double representing the job status (0 if it hasn't began) or -1 if there's an error 
     */
    getJobStatus() {
      let jobId = localStorage.getItem("jobId");
      axios.get(`${API_URL}/job-status/${jobId}`)
        .then(res => res.ok ? res.data.jobStatus : -1)
    },

    getJobResult() {
        console.log("getJobResult() is yet unimplemented");
    }
}

export default ImageProcessorService;