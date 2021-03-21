import React, { useEffect, useState } from 'react'
import ProcessedImagesList from './ProcessedImagesList';
import ProgressBar from './ProgressBar';

export default function ImageDownloader(props) {
    
    const API_URL = window.REACT_APP_API_URL;

    const [status, setStatus] = useState(0)
    const [processedImages, setProcessedImages] = useState([]);
    
    const REFRESH_IMAGES = 1000;

    function getJobResult() {
        console.log("Getting job result");
        let jobId = localStorage.getItem("jobId");
        fetch(`${API_URL}/job/${jobId}/result`)
            .then(res => res.json())
            .then(res => setProcessedImages(res.images))
            // .then(res => console.log(res.images))
      }

     /**
     * Checks if job has ended until it does
     */
    useEffect(() => {
        let intervalId = setInterval(()=> {            
            let url = `${API_URL}/job/${localStorage.getItem("jobId")}/status`;
            fetch(url, {cache: "no-cache"})
                .then(res => res.json())
                .then(data => {
                    console.log(data);
                    setStatus(data.status)
                    if (data.status >= 100){
                        clearInterval(intervalId);
                        getJobResult();
                    }
                })
                .catch(err => setStatus(0));
        }, REFRESH_IMAGES);
    }, []);


    return (
        <div>                        
            <p>{ status < 100 ? "Procesando imágenes..." : "Procesamiento finalizado!"}</p>
            <ProgressBar status={status}/> 
            {processedImages ?            
                <ProcessedImagesList expectedImages={props.expectedImages} receivedImages={processedImages}/>        
            :   <ProcessedImagesList expectedImages={props.expectedImages} receivedImages={[]}/>       
            }
        </div>
    )
}