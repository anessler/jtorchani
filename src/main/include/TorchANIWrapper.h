
#ifndef TORCHANI_WRAPPER_H_
#define TORCHANI_WRAPPER_H_

#ifndef TORCHANI_EXPORT
#define TORCHANI_EXPORT
#endif

/* Type Declarations */

typedef struct TorchJITModule_struct TorchJITModule;
typedef struct Species_struct species;
typedef struct Coordinates_struct coordinates;
typedef struct Gradient_struct gradient;

#if defined(__cplusplus)
extern "C" {
#endif

extern TORCHANI_EXPORT double ctorch(const char* pathToTorch, long n, const int species[], double coordinates[], double gradient[]);


#if defined(__cplusplus)
}
#endif

#endif /*TORCHANI_WRAPPER_H_*/
