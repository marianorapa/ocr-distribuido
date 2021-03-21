import React from 'react'
import UploadedImage from './UploadedImage'

export default function UploadedImagesList(props) {
    return (
        <div className="row justify-content-center mt-3">
            {props.images ? props.images.map(image => {   
                console.log("Image: " + image);
                return (             
                    <UploadedImage image={image} key={image.name}/>                    
                )
            }): null}
        </div>
    )
}   
