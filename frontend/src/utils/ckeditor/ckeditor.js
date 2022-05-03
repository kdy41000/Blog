import React from 'react';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import 'utils/ckeditor/ckeditor.css';
import http from 'http/customAxios';

const API_URL = "http://localhost:8080/ckeditor";

export const CustomCKEditor = ({setContent}) => {

function uploadAdapter(loader) {
    return {
      upload: () => {
        return new Promise((resolve, reject) => {
          const formData = new FormData();
          loader.file.then((file) => {
            console.log("file:",file);
            formData.append("file", file);
            // let headers = new Headers();
            // headers.append("Origin", "http://localhost:3000");
            http.post('/api/v1/manager/board/uploadfiles', formData, {
              headers: { "Content-Type" : "multipart/form-data"}
            })
              .then((res) => {
                console.log("${API_URL}/${res.url:",`${API_URL}/${res.url}`);
                resolve({
                  default: `${API_URL}/${res.url}`
                });
              })
              .catch((err) => {
                reject(err);
              });
          });
        });
      }
    };
  }
function uploadPlugin(editor) {
    editor.plugins.get("FileRepository").createUploadAdapter = (loader) => {
      return uploadAdapter(loader);
    };
  }
    return (
        <div>
            <h2>Using CKEditor 5 build in React</h2>
                <CKEditor
                 config={{
                    extraPlugins: [uploadPlugin]
                  }}
                    editor={ ClassicEditor }
                    data="<p>Hello from CKEditor 5!</p>"
                    onReady={ editor => {
                        // You can store the "editor" and use when it is needed.
                        console.log( 'Editor is ready to use!', editor );
                    } }
                    onChange={ ( event, editor ) => {
                        const data = editor.getData();
                        console.log( { event, editor, data } );
                        setContent(data);
                    } }
                    onBlur={ ( event, editor ) => {
                        console.log( 'Blur.', editor );
                    } }
                    onFocus={ ( event, editor ) => {
                        console.log( 'Focus.', editor );
                    } }
                />
        </div>
    );
};