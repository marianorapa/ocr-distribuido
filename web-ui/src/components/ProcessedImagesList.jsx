import React from 'react'
import ProcessedImage from './ProcessedImage'

export default function ProcessedImagesList({expectedImages, receivedImages}) {
    
    return (
        <div className="m-3">                        
            {expectedImages.map(image => 
                <ProcessedImage key={image.name} expectedImage={image} receivedImage={receivedImages.find(received => received.filename === image.name)}/>
            )}
        </div>
    )
}
