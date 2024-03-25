const express = require('express')
const morgan = require('morgan')
const path = require('path')
const logger = morgan('dev')
const PORT = 3000
const app = express()

app.use(logger)

app.use('/static', express.static(path.join(__dirname, 'public')))
app.use('/static', express.static(path.join(__dirname, 'frontend/src')))
app.use('/static', express.static(path.join(__dirname, 'frontend/stylesheets')))

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'frontend/index.html'))
})

app.listen(PORT, () => console.log(`Server is now listening on port ${PORT}`))