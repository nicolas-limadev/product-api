FROM node:18-alpine

ENV HOME=/home/app
WORKDIR $HOME

COPY package.json ./
RUN npm install
COPY . .
RUN npm run build && npm install -g serve


EXPOSE 3000

CMD [ "serve", "-s", "build" ]
