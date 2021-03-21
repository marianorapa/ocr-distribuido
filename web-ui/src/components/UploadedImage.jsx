import React from 'react';
import '../styles/uploadedImage.css'

export default function UploadedImage(props) {
    console.log("Image to render: " + props.image);
    return (
        <div className="col-sm-3 mt-2">
            <img className="uploadedImage" src={URL.createObjectURL(props.image)} alt=""/>
        </div>
    )
}
