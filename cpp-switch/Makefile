CXX = g++
CXXFLAGS = -Wall -std=c++17

SRC = main.cpp
OBJ = $(SRC:.cpp=.o)
TARGET = dsa

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CXX) $(OBJ) -o $(TARGET)

%.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@

clean:
	rm -f *.o $(TARGET)
