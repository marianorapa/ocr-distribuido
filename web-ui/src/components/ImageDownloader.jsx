import React, { useEffect, useState } from 'react'
import ImageProcessorService from '../services/ImageProcessorService';
import ProcessedImagesList from './ProcessedImagesList';
import ProgressBar from './ProgressBar';

export default function ImageDownloader(props) {
    
    const [status, setStatus] = useState(0)
    const [processedImages, setProcessedImages] = useState([]);
    
    const REFRESH_IMAGES = 5000;

    /**
     * Gets the job result from the service
     */
    const getJobResult = () => {
        let result = ImageProcessorService.getJobResult();       
        setProcessedImages(result.processedImages);
    }

    /**
     * Asks the service if the job is done. When it is, gets the result
     */
    const checkJobFinalized = () => {
        let status = ImageProcessorService.getJobStatus();
        setStatus(status);
        if (status >= 100) {
            getJobResult();
            return true;
        }
        return false;
    }


    /**
     * Constantly checks if job has ended
     */
    useEffect(() => {
        let intervalId = setInterval(()=> {
            let finalized = checkJobFinalized();
            if (finalized) {
                clearInterval(intervalId);
            }
        }, REFRESH_IMAGES);
    }, []);



    return (
        <div>                        
            <p>{ status < 100 ? "Processing images..." : "Images done processing!"}</p>
            <ProgressBar status={status}/>            
            <ProcessedImagesList expectedImages={props.expectedImages} receivedImages={processedImages}/>        
        </div>
    )
}
