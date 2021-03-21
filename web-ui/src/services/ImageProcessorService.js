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
    async getJobStatus() {
      let jobId = localStorage.getItem("jobId");
      axios.get(`${API_URL}/job/${jobId}/status`)
        //.then(res => res.ok ? res.data.jobStatus : -1)
        .then(res => res.data)
        .then(data => data.status)
        .catch(err => 0)

    },

    getJobResult() {
      let jobId = localStorage.getItem("jobId");
      axios.get(`${API_URL}/job/${jobId}/result`)
        .then(res => res.ok ? res.data.jobStatus : -1)
    }
}

export default ImageProcessorService;