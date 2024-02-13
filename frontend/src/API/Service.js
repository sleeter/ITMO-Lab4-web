import axios from "axios";

export default class Service {
    static path = 'http://localhost:8080'

    static async signUpReq(email, password) {
        return await axios.post(Service.path + "/auth/signup", {
            login: email,
            password: password,
        }).then(res => res.data);
    }

    static async signInReq(email, password) {
        return await axios.post(this.path + '/auth/login', {
            login: email,
            password: password,
        }).then(res => res.data)
    }

    static async sendHit(hit, token) {
        return axios.post(this.path + '/point/add', {
            x: Number(hit.x),
            y: Number(hit.y),
            r: Number(hit.r),
        }, {
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-type": "Application/json",
                "Authorization": "Bearer " + token
            }
        })
            .then(res => res.data)
            .catch(function (error) {
                if (error.response) {
                    return null;
                }
            })
    }
    static async deleteHits(token) {
        return axios.delete(this.path + '/point/delete', {
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-type": "Application/json",
                "Authorization": "Bearer " + token
            }
        })
            .then(res => res.data)
            .catch(function (error) {
                if (error.response) {
                    return null;
                }
            })
    }

    static async getHitsForUser(token) {
        return await axios.get(this.path + '/point/all', {
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-type": "Application/json",
                "Authorization": "Bearer " + token
            }
        }).then(res => res.data).catch(function (error) {
            console.log("Cant get data for this user")
        })
    }
}
