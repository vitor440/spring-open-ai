import Box from '@mui/material/Box'
import Button from '@mui/material/Button'
import TextField from '@mui/material/TextField'
import Typography from '@mui/material/Typography'
import React, { useState } from 'react'
import api from './service/api'
import CircularProgress from '@mui/material/CircularProgress'
import LinearProgress from '@mui/material/LinearProgress'

const Chat = () => {

  
  const [response, setResponse] = useState(null)
  const [input, setInput] = useState(null)
  const [loading, setLoading] = useState(false)

  
  const handleClick = async () => {
    
    setResponse(null)
    try {
        setLoading(true)
        const result = await api.get("/ai/chat", {
            params: {
                prompt: input
            }
        })

        const data = result.data
        setResponse(data)

    } catch (error) {
        
    }
    setLoading(false)
  }

  return (
    <Box sx={{display:"flex", alignItems:"center", flexDirection:"column"}}>
          <Typography variant='h6' sx={{mb:2}}>O que devemos explorar ?</Typography>
          <TextField label="Faça uma pergunta" sx={{width:"50%"}} multiline onChange={(e) => setInput(e.target.value)}/>
          <Button sx={{mt:2, width:100}} variant='contained' onClick={handleClick} disabled={loading && true}>
            {loading?  <CircularProgress size={"20px"} color='#fff'/>: "Perguntar"}
          </Button>
            
            {response &&
              <Box sx={{display:"block", backgroundColor:"#ebe7e7", mt:3, width:"50%", minHeight:"50px"}}>

                
                {response}
              </Box>
            }
        </Box>
  )
}

export default Chat