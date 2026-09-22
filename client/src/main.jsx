import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterContextProvider, RouterProvider } from 'react-router-dom'
import Chat from './Chat.jsx'
import Image from './Image.jsx'
import Transcription from './Transcription.jsx'


const router = createBrowserRouter([
  {
    path: "/",
    element: <App/>,
    children:[
      {
        path: "chat",
        element: <Chat/>
      },
      {
        path: "image",
        element: <Image/>
      },
      {
        path: "transcription",
        element: <Transcription/>
      }
    ]
  }
])

createRoot(document.getElementById('root')).render(


  <StrictMode>
    
      <RouterProvider router={router}/>
    
  </StrictMode>
)
