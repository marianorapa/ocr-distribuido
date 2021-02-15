import React from 'react'
import UploadedImage from './UploadedImage'

export default function UploadedImagesList(props) {
    return (
        <div className="row justify-content-center">
            {props.images ? props.images.map(image => {   
                return (             
                    <UploadedImage image={image} key={image.name}/>                    
                )
            }): null}
        </div>
    )
}   
