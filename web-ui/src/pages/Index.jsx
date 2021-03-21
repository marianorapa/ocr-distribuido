import React, { useState } from 'react'
import ImageDownloader from '../components/ImageDownloader';
import ImageUploader from '../components/ImageUploader';
import axios from 'axios';

export default function Index() {

    const API_URL = window.REACT_APP_API_URL;

    const [waitingForUpload, setWaitingForUpload] = useState(true);
    
    const [pictures, setPictures] = useState([]);

     const processImages = (pics) => {
        localStorage.removeItem("jobId");
        
        setPictures(pics);
        
        const formData = new FormData()
        pics.forEach((pic, i) => {        
            formData.append("images", pic)
        })
        
        axios.post(`${API_URL}/process-images`,  formData)
            .then(res => res.data)
            .then(data => {
                localStorage.setItem("jobId", data.jobId);
            })

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

