FROM python:3.8
ENV PYTHONUNBUFFERED=1
WORKDIR /code
COPY requirements.txt /code/
RUN pip install -r requirements.txt
COPY . /code/
ENTRYPOINT [ "python" ]
CMD ["manage.py", "runserver","0.0.0.0:8000"]
