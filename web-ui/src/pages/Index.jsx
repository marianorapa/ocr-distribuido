import React, { useState } from 'react'
import ImageDownloader from '../components/ImageDownloader';
import ImageUploader from '../components/ImageUploader';

import ImageProcessorService from '../services/ImageProcessorService';

export default function Index() {

    const [waitingForUpload, setWaitingForUpload] = useState(true);
    
    const [pictures, setPictures] = useState([]);

    const processImages = (pics) => {
        ImageProcessorService.processImages(pictures);
        setPictures(pics);
        setWaitingForUpload(false);        
    }

    return (
        <div className="container">
            <div className="card shadow mt-5">
                <div className="card-body">
                    <div className="card-title">
                        <h2 className="m-2">OCR de imágenes</h2>
                    </div>        
                    { waitingForUpload ?
                            <ImageUploader processImages={processImages}/>
                        :   <ImageDownloader expectedImages={pictures}/>
                    }
                </div>
            </div>
        </div>
    )
}

