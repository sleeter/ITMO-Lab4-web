FROM node:latest

EXPOSE 3000
WORKDIR /opt/frontend
COPY package.json .
COPY package-lock.json .
RUN npm install
COPY . .
ENTRYPOINT npm start