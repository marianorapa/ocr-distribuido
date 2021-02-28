import React, { useRef, useState } from 'react';
import UploadedImagesList from './UploadedImagesList';
import { FaUpload } from 'react-icons/fa';


export default function ImageUploader(props) {

    const [pictures, setPictures] = useState([]);
    const [alreadyUploadedPictures, setAlreadyUploadedPictures] = useState([]);

    const inputFile = useRef(null) ;

    const onSubirClick = () => {
        inputFile.current.click();
    }


    const pictureAlreadyUploaded = file => {
        setAlreadyUploadedPictures([...alreadyUploadedPictures, file])
    }

    const onChangeInput = () => {              

        setAlreadyUploadedPictures([]);

        let files = inputFile.current.files;

        for (var i = 0; i < files.length; i++) {   
            let file = files[i];
        
            if (pictures.find(p => p.name === file.name) === undefined) {                
                console.log("Pic added!");
                setPictures([...pictures, file]);
            }
            else {             
                console.log("Pic already there")
                pictureAlreadyUploaded(file);
            }
        }        
    }


    const processImages = () => {   
        console.log(`Pictures to be processed ${pictures}`);             
        props.processImages(pictures);
    }

    return (
        <div>        
            <p>Cargue las imágenes para efectuar un reconocimiento óptico de caracteres</p>
            <div className="text-center">
                <div>
                    <FaUpload className="h3"/>
                </div>
                <div className="small">
                    <p>Máx. 5mb. Formatos: jpg, png</p>
                </div>
                
                <form action="">
                    <input className="d-none" type="file" ref={inputFile} onChange={onChangeInput} multiple={true}/>
                </form>

                <a className="btn btn-sm small px-1 py-0 bg-dark text-white" onClick={onSubirClick}>Subir imagen</a>
            
                { alreadyUploadedPictures.length > 0 &&
                    <div>
                        <p>Imagen/es repetida/s:</p>
                        <ul>
                        {alreadyUploadedPictures.map(picture => {
                            return(
                            <li>{picture.name}</li>   
                            )
                        })}
                        </ul>
                    </div>
                }

                <UploadedImagesList images={pictures}/>
            </div>
            <div className="d-flex justify-content-md-end justify-content-center mt-4">
                <a className="btn btn-primary" onClick={processImages}>Procesar imágenes</a>
            </div>
        </div>
       
    )
}